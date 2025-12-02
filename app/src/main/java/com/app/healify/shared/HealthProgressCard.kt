package com.app.healify.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HealthProgressCard(percent: Float,) {
    val gradientColors = listOf(
        Color(0xFF7ED957),
        Color(0xFF3AB0A2)
    )
    Box(
        modifier = Modifier
            .fillMaxWidth(0.93f)
            .fillMaxHeight(0.3f)
            .background(
                brush = Brush.linearGradient(
                    colors = gradientColors,
                    start = Offset(0f, 0f),
                    end = Offset(1000f, 500f)
                ),
                shape = MaterialTheme.shapes.extraLarge
            ).padding(20.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
            modifier = Modifier.fillMaxWidth().fillMaxHeight()
        ){
            LargeText(
                text = "Phone Health",
            )
            Spacer(modifier = Modifier.height(10.dp))
            DeterminateGradientCircularLoader(
                percent = percent.coerceIn(0f, 1f),
                size = 130.dp,
                stroke = 10.dp
            )
        }
    }
}
