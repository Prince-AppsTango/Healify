package com.app.healify.helpers
import android.content.Context
import android.os.BatteryManager
import android.os.Environment
import android.os.StatFs
import com.app.healify.models.BatteryHealthModel
import com.app.healify.models.StorageHealthModel

class BatteryHelper(private val context: Context) {

    fun getBatteryHealth(): BatteryHealthModel {
        val bm = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
        val percent = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        return BatteryHealthModel(
            percentage = percent,
            status =  when {
                percent >= 80 -> "Good"
                percent >= 50 -> "Average"
                percent >= 20 -> "Poor"
                else -> "Critical"
            }
        )
    }

    fun getStorageHealth(): StorageHealthModel {
        val stat = StatFs(Environment.getDataDirectory().path)
        val totalBytes = stat.totalBytes
        val freeBytes = stat.availableBytes
        val usedBytes = totalBytes - freeBytes
        val gb = 1024.0 * 1024 * 1024
        return StorageHealthModel(
            totalGB = totalBytes.toDouble() / gb,
            freeGB = freeBytes.toDouble() / gb,
            usedGB = usedBytes.toDouble() / gb
        )
    }
}
