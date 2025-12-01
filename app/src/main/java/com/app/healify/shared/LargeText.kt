package com.app.healify.shared

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun LargeText(text: String, modifier: Modifier = Modifier) {
    Text(text, modifier = modifier, fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color.White, textAlign = TextAlign.Center)
}