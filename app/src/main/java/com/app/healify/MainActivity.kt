package com.app.healify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import com.app.healify.ui.theme.HealifyTheme
import com.app.healify.views.PhoneHealthScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Ensure status bar is visible
        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = true // Dark icons on light background
        }

        setContent {
            HealifyTheme {
                PhoneHealthScreen()
            }
        }
    }
}