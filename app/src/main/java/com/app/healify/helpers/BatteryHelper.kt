package com.app.healify.helpers
import android.app.ActivityManager
import android.content.Context
import android.os.BatteryManager
import android.os.Environment
import android.os.StatFs
import com.app.healify.models.BatteryHealthModel
import com.app.healify.models.CpuInfo
import com.app.healify.models.RamInfo
import com.app.healify.models.StorageHealthModel
import java.io.File

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

    fun getRamInfo(context: Context): RamInfo {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val memoryInfo = ActivityManager.MemoryInfo()
        activityManager.getMemoryInfo(memoryInfo)

        val totalRam = memoryInfo.totalMem.toDouble() / (1024 * 1024 * 1024) // GB
        val availableRam = memoryInfo.availMem.toDouble() / (1024 * 1024 * 1024) // GB
        val usedRam = totalRam - availableRam

        return RamInfo(
            totalGB = String.format("%.2f", totalRam).toDouble(),
            usedGB = String.format("%.2f", usedRam).toDouble(),
            freeGB = String.format("%.2f", availableRam).toDouble()
        )
    }


    fun getCpuInfo(): CpuInfo {
        val cpuName = getCpuName()
        val cores = Runtime.getRuntime().availableProcessors()
        val arch = System.getProperty("os.arch") ?: "Unknown"
        val freqs = getCpuFrequencies()

        return CpuInfo(
            cpuName = cpuName,
            cores = cores,
            architecture = arch,
            frequenciesMHz = freqs
        )
    }

    private fun getCpuName(): String {
        return try {
            val reader = File("/proc/cpuinfo").readLines()
            val line = reader.firstOrNull { it.startsWith("Hardware") || it.startsWith("model name") }
            line?.substringAfter(":")?.trim() ?: "Unknown"
        } catch (e: Exception) {
            "Unknown"
        }
    }

    private fun getCpuFrequencies(): List<Int> {
        val result = mutableListOf<Int>()
        try {
            val cpuCount = Runtime.getRuntime().availableProcessors()
            for (i in 0 until cpuCount) {
                val file = File("/sys/devices/system/cpu/cpu$i/cpufreq/cpuinfo_max_freq")
                if (file.exists()) {
                    val mhz = file.readText().trim().toInt() / 1000
                    result.add(mhz)
                }
            }
        } catch (_: Exception) {}
        return result
    }


}
