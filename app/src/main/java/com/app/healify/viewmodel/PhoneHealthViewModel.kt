package com.app.healify.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.app.healify.helpers.BatteryHelper
import com.app.healify.models.PhoneHealthModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PhoneHealthViewModel(private val batteryHelper: BatteryHelper) : ViewModel() {
    private val _healthPercent = MutableStateFlow(0f) // 0f..1f
    val healthPercent = _healthPercent.asStateFlow()

    private val  _hasRunDiagnostics = MutableStateFlow<List<PhoneHealthModel>>(emptyList())
    val hasRunDiagnostics = _hasRunDiagnostics.asStateFlow()

    fun runFullDiagnostics(context: Context) {
        val battery = batteryHelper.getBatteryHealth()
        val storage = batteryHelper.getStorageHealth()
        val ramInfo = batteryHelper.getRamInfo(context)
        val cpuInfo = batteryHelper.getCpuInfo()
        val cameraInfo = batteryHelper.getCameraInfo()
        val microphoneInfo = batteryHelper.getMicrophoneInfo()
        val networkInfo = batteryHelper.getNetworkInfo()
        val sensorInfo = batteryHelper.getSensorInfo()
        val displayInfo = batteryHelper.getDisplayInfo()
        val bluetoothInfo = batteryHelper.getBluetoothInfo()
        val systemInfo = batteryHelper.getSystemInfo()
        val nfcInfo = batteryHelper.getNfcInfo()
        val gpsInfo = batteryHelper.getGpsInfo()
        val speakerInfo = batteryHelper.getSpeakerInfo()

        val batteryScore = battery.percentage.toFloat() // 0-100
        val storageScore = ((storage.freeGB / storage.totalGB) * 100).toFloat() // 0-100
        val ramScore = ((ramInfo.freeGB / ramInfo.totalGB) * 100).toFloat() // 0-100
        val cpuScore = if (cpuInfo.cores >= 4) 100f else (cpuInfo.cores * 25f) // 0-100

        val cameraScore = if (cameraInfo.isAvailable) 100f else 0f
        val microphoneScore = if (microphoneInfo.isAvailable) 100f else 0f
        val networkScore = if (networkInfo.isConnected) 100f else 50f
        val sensorScore = if (sensorInfo.totalSensors > 10) 100f else (sensorInfo.totalSensors * 10f).coerceAtMost(100f)
        val displayScore = if (displayInfo.screenSizeInches > 0) 100f else 0f
        val bluetoothScore = when {
            bluetoothInfo.isEnabled -> 100f
            bluetoothInfo.isSupported -> 75f
            else -> 50f
        }
        val nfcScore = when {
            nfcInfo.isEnabled -> 100f
            nfcInfo.isSupported -> 75f
            else -> 50f
        }
        val gpsScore = when {
            gpsInfo.isEnabled -> 100f
            gpsInfo.isSupported -> 75f
            else -> 50f
        }
        val speakerScore = if (speakerInfo.isAvailable) 100f else 0f

        val score = batteryScore * 0.20f +      // Battery: 20%
                storageScore * 0.15f +          // Storage: 15%
                ramScore * 0.15f +              // RAM: 15%
                cpuScore * 0.10f +              // CPU: 10%
                cameraScore * 0.08f +           // Camera: 8%
                microphoneScore * 0.05f +       // Microphone: 5%
                networkScore * 0.08f +          // Network: 8%
                sensorScore * 0.05f +           // Sensors: 5%
                displayScore * 0.04f +          // Display: 4%
                bluetoothScore * 0.03f +        // Bluetooth: 3%
                nfcScore * 0.02f +              // NFC: 2%
                gpsScore * 0.03f +              // GPS: 3%
                speakerScore * 0.02f            // Speaker: 2%
                                                // Total: 100%

        Log.d("PhoneHealthVM", "Calculated Score: $score")
        Log.d("PhoneHealthVM", "Battery: $batteryScore, Storage: $storageScore, RAM: $ramScore")
        _healthPercent.value = (score / 100f).coerceIn(0f, 1f)
        _hasRunDiagnostics.value = listOf(
            PhoneHealthModel(
                data = battery,
                storageHealthModel = storage,
                ramInfo = ramInfo,
                cpuInfo = cpuInfo,
                cameraInfo = cameraInfo,
                microphoneInfo = microphoneInfo,
                networkInfo = networkInfo,
                sensorInfo = sensorInfo,
                displayInfo = displayInfo,
                bluetoothInfo = bluetoothInfo,
                systemInfo = systemInfo,
                nfcInfo = nfcInfo,
                gpsInfo = gpsInfo,
                speakerInfo = speakerInfo
            )
        )
    }
}
