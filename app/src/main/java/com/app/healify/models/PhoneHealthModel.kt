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
    val cpuInfo: CpuInfo? = null
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
