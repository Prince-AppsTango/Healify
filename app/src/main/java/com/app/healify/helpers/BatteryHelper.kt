package com.app.healify.helpers
import android.content.Context
import android.os.BatteryManager
import android.util.Log

class BatteryHelper(private val context: Context) {

    fun getBatteryHealth(): String {
        val bm = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
        val percent = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        Log.d("BatteryHelper", "Battery percentage: $percent")
        return when {
            percent >= 80 -> "Good"
            percent >= 50 -> "Average"
            percent >= 20 -> "Poor"
            else -> "Critical"
        }
    }
}
