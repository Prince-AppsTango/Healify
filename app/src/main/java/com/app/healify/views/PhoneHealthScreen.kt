package com.app.healify.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
    val phoneHealthStatus = viewModel.hasRunDiagnostics.collectAsState()

    SafeArea {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState())
                .background(color = Color.White)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                HealthProgressCard(percent = healthPercent.value)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(modifier = Modifier.padding(horizontal = 15.dp)) {
                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()){
                    CategoryCard(
                        text = "Battery",
                        subTitle = if(phoneHealthStatus.value.firstOrNull()?.data?.percentage != null) "${phoneHealthStatus.value.firstOrNull()?.data?.percentage}% ${phoneHealthStatus.value.firstOrNull()?.data?.status?.toString()}" else "N/A",
                        color = when(phoneHealthStatus.value.firstOrNull()?.data?.status) {
                            "Good" -> Color(0xFF4CAF50)
                            "Average" -> Color(0xFFFFC107)
                            "Poor" -> Color(0xFFFF5722)
                            "Critical" -> Color(0xFFF44336)
                            else -> Color.Gray
                        }
                    )
                    CategoryCard(
                        text = "Storage",
                        subTitle = "${ phoneHealthStatus.value.firstOrNull()?.storageHealthModel?.freeGB?.toInt() ?: 0} GB / ${ phoneHealthStatus.value.firstOrNull()?.storageHealthModel?.totalGB?.toInt() ?: 0} GB",
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()){
                    CategoryCard(
                        text = "RAM",
                        subTitle = if(phoneHealthStatus.value.firstOrNull()?.ramInfo?.freeGB != null)  "Free: ${phoneHealthStatus.value.firstOrNull()?.ramInfo?.freeGB?.toInt() } GB"  else "N/A",
                        color = if ((phoneHealthStatus.value.firstOrNull()?.ramInfo?.freeGB ?: 0.0) >= 2.0) Color.Black else Color.Gray
                    )
                    CategoryCard(
                        text = "Cores",
                        subTitle = if( phoneHealthStatus.value.firstOrNull()?.cpuInfo?.cores != null) "${ phoneHealthStatus.value.firstOrNull()?.cpuInfo?.cores}" else "N/A",
                        color =  if( phoneHealthStatus.value.firstOrNull()?.cpuInfo?.cores != null) Color.Black else Color.Gray
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
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
                            viewModel.runFullDiagnostics(context)
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
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}
