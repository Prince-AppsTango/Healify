package com.app.healify.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CategoryCard(text: String, modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .width(180.dp)
        .border(width = 0.5.dp, color = Color.LightGray.copy(alpha = 0.3f), shape = MaterialTheme.shapes.large)
        .shadow(
            elevation = 10.dp,
            shape = MaterialTheme.shapes.large,
            ambientColor = Color.LightGray,
            spotColor = Color.LightGray
        )
        .background(color = Color.White, shape = MaterialTheme.shapes.large)
        .padding(10.dp)
    ) {
        Row {
            Image(Icons.Default.Home, contentDescription = "icon", modifier = Modifier.size(40.dp))
            Spacer(modifier= Modifier.width(10.dp))
            Column {
                Text(
                    text,
                    color = Color.Black,
                    fontWeight = FontWeight.W500,
                    fontSize = 18.sp,
                )
                Text(
                    "Good",
                    color = Color.Black,
                    fontWeight = FontWeight.W500,
                    fontSize = 16.sp,
                )
                Text(
                    "Temp: 35°C",
                    color = Color.Gray,
                    fontWeight = FontWeight.W400,
                    fontSize = 16.sp,
                )
            }
        }
    }
}