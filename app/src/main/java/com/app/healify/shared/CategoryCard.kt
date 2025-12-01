package com.app.healify.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
fun CategoryCard(modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .height(90.dp)
        .width(180.dp)
        .shadow(
            elevation = 10.dp,
            shape = MaterialTheme.shapes.large,
            ambientColor = Color.Black,
            spotColor = Color.Black
        )
        .background(color = Color.White, shape = MaterialTheme.shapes.large)
        .padding(10.dp)
    ) {
        Row {
            Image(Icons.Default.Home, contentDescription = "icon", modifier = Modifier.size(40.dp))
            Spacer(modifier= Modifier.width(10.dp))
            Column {
                Text(
                    "Battery",
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