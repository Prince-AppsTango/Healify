package com.app.healify.models

data class StorageHealthModel(
    val totalGB: Double,
    val freeGB: Double,
    val usedGB: Double
)

data class BatteryHealthModel(
    val percentage: Int,
    val status: String
)

data class PhoneHealthModel(
    val data: BatteryHealthModel,
    val storageHealthModel: StorageHealthModel,
    val ramInfo: RamInfo? = null,
    val cpuInfo: CpuInfo? = null,
    val cameraInfo: CameraInfo? = null,
    val microphoneInfo: MicrophoneInfo? = null,
    val networkInfo: NetworkInfo? = null,
    val sensorInfo: SensorInfo? = null
)

data class RamInfo(
    val totalGB: Double,
    val usedGB: Double,
    val freeGB: Double
)

data class CpuInfo(
    val cpuName: String,
    val cores: Int,
    val architecture: String,
    val frequenciesMHz: List<Int>
)

data class CameraInfo(
    val isAvailable: Boolean,
    val numberOfCameras: Int
)

data class MicrophoneInfo(
    val isAvailable: Boolean
)

data class NetworkInfo(
    val isConnected: Boolean,
    val connectionType: String,
    val linkSpeedMbps: Int
)

data class SensorInfo(
    val totalSensors: Int,
    val accelerometerAvailable: Boolean,
    val gyroscopeAvailable: Boolean,
    val proximityAvailable: Boolean,
    val lightSensorAvailable: Boolean
)

