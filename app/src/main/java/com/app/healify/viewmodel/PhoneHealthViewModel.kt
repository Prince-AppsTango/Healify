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

        val score = battery.percentage * 0.3f +
                ((storage.freeGB / storage.totalGB) * 100).toFloat() * 0.2f +
                ((ramInfo.freeGB / ramInfo.totalGB) * 100).toFloat() * 0.2f +
                70f * 0.15f
        Log.d("PhoneHealthVM", "Calculated Score: $score")
        _healthPercent.value = (score / 100f)
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
