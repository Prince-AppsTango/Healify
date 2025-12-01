package com.app.healify.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.healify.helpers.BatteryHelper
import com.app.healify.shared.HealthProgressCard
import com.app.healify.utils.PhoneHealthVMFactory
import com.app.healify.utils.SafeArea
import com.app.healify.viewmodel.PhoneHealthViewModel
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.healify.shared.CategoryCard

@Composable
fun PhoneHealthScreen() {
    val context = LocalContext.current

    val gradientColors = listOf(
        Color(0xFF7ED957),
        Color(0xFF3AB0A2)
    )
    val viewModel: PhoneHealthViewModel = viewModel(
        factory = PhoneHealthVMFactory(BatteryHelper(context))
    )
    val healthPercent  =  viewModel.healthPercent.collectAsState()

    val textList = listOf<String>(
        "Battery",
        "Storage",
        "Memory",
        "Sensors",
        "Camera",
        "Network",
        "Speakers",
        "Microphone",
    )
    SafeArea {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                HealthProgressCard(percent = healthPercent.value)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Box(modifier = Modifier.padding(horizontal = 15.dp)) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxHeight(0.85f),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    content = {
                        items(textList.size) { item ->
                            CategoryCard(
                                text = textList[item],
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }
                    }
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.95f)
                        .background(
                            brush = Brush.linearGradient(
                                colors = gradientColors,
                                start = Offset(0f, 0f),
                                end = Offset(1000f, 500f)
                            ),
                            shape = MaterialTheme.shapes.large
                        )
                        .clickable {
                            viewModel.runFullDiagnostics()
                        }
                        .padding(5.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "RUN FULL DIAGNOSTICS",
                        modifier = Modifier.padding(15.dp),
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W500
                    )
                }
            }
        }
    }
}
