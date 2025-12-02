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
    val sensorInfo: SensorInfo? = null,
    val displayInfo: DisplayInfo? = null,
    val bluetoothInfo: BluetoothInfo? = null,
    val systemInfo: SystemInfo? = null,
    val nfcInfo: NfcInfo? = null,
    val gpsInfo: GpsInfo? = null,
    val speakerInfo: SpeakerInfo? = null
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

data class DisplayInfo(
    val screenSizeInches: Float,
    val resolutionWidth: Int,
    val resolutionHeight: Int,
    val refreshRate: Float,
    val density: Int,
    val brightnessLevel: Int
)

data class BluetoothInfo(
    val isSupported: Boolean,
    val isEnabled: Boolean,
    val deviceName: String,
    val bluetoothVersion: String
)

data class SystemInfo(
    val androidVersion: String,
    val sdkVersion: Int,
    val deviceManufacturer: String,
    val deviceModel: String,
    val deviceBrand: String
)

data class NfcInfo(
    val isSupported: Boolean,
    val isEnabled: Boolean
)

data class GpsInfo(
    val isEnabled: Boolean,
    val isSupported: Boolean,
    val providerCount: Int
)

data class SpeakerInfo(
    val isAvailable: Boolean,
    val maxVolume: Int,
    val currentVolume: Int
)

