package com.app.healify.shared


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.foundation.Canvas
import androidx.compose.ui.graphics.Brush.Companion.sweepGradient
@Composable
fun DeterminateGradientCircularLoader(
    percent: Float,
    modifier: Modifier = Modifier,
    size: Dp = 200.dp,
    stroke: Dp = 18.dp,
    animationDuration: Int = 900
) {
    val target = percent.coerceIn(0f, 1f)

    val animatedProgress = remember { Animatable(0f) }
    LaunchedEffect(target) {
        animatedProgress.animateTo(target, animationSpec = tween(durationMillis = animationDuration))
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.size(size)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val strokePx = stroke.toPx()
            val diameter = this.size.minDimension - strokePx
            val topLeft = Offset(strokePx / 2f, strokePx / 2f)
            val arcSize = Size(diameter, diameter)

            drawArc(
                color = Color.White.copy(alpha = 0.14f),
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                topLeft = topLeft,
                size = arcSize,
                style = Stroke(width = strokePx, cap = androidx.compose.ui.graphics.StrokeCap.Round)
            )

            val gradientColors = listOf(
                Color(0xFF9EE48F), // lighter green
                Color(0xFF7ED957),
                Color(0xFF3AB0A2),
                Color(0xFF38A6C9)  // slight blueish
            )

            val sweep = sweepGradient(
                colors = gradientColors,
                center = this.center
            )

            // progress sweep angle
            val sweepAngle = animatedProgress.value * 360f

            // draw progress arc with gradient brush
            drawArc(
                brush = sweep,
                startAngle = -90f,
                sweepAngle = sweepAngle,
                useCenter = false,
                topLeft = topLeft,
                size = arcSize,
                style = Stroke(width = strokePx, cap = androidx.compose.ui.graphics.StrokeCap.Round)
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            val percentInt = (animatedProgress.value * 100).toInt()
            Text(
                text = "$percentInt%",
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = when {
                    percent >= 0.9f -> "EXCELLENT"
                    percent >= 0.8f -> "GOOD"
                    percent >= 0.7f -> "AVERAGE"
                    percent >= 0.5f -> "FAIR"
                    else -> "POOR"
                },
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White.copy(alpha = 0.9f)
            )
        }
    }
}
