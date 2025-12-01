package com.app.healify.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.healify.helpers.BatteryHelper
import com.app.healify.shared.HealthProgressCard
import com.app.healify.utils.PhoneHealthVMFactory
import com.app.healify.utils.SafeArea
import com.app.healify.viewmodel.PhoneHealthViewModel
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.app.healify.shared.CategoryCard

@Composable
fun PhoneHealthScreen() {
    val context = LocalContext.current
    val viewModel: PhoneHealthViewModel = viewModel(
        factory = PhoneHealthVMFactory(BatteryHelper(context))
    )
    SafeArea {
        Column(modifier = Modifier.fillMaxSize().background(color =Color.White)){
            Spacer(modifier = Modifier.height(20.dp))
            Box(contentAlignment = Alignment.Center , modifier = Modifier.fillMaxWidth()) {
                HealthProgressCard()
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceEvenly) {
                CategoryCard()
                CategoryCard()
            }
        }
    }
}
