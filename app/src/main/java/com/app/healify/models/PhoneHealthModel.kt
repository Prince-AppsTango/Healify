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
    val ramInfo: RamInfo? = null
)

data class RamInfo(
    val totalGB: Double,
    val usedGB: Double,
    val freeGB: Double
)