package com.app.healify.viewmodel

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

    fun runFullDiagnostics() {
        val battery = batteryHelper.getBatteryHealth()
        val storage = batteryHelper.getStorageHealth()
        val score = battery.percentage * 0.3f +
                ((storage.freeGB / storage.totalGB) * 100).toFloat() * 0.2f
        Log.d("PhoneHealthVM", "Calculated Score: $score")
        _healthPercent.value = (score / 100f)
        _hasRunDiagnostics.value = listOf(
            PhoneHealthModel(
                data = battery,
                storageHealthModel = storage
            )
        )
    }
}

//val score =
//    battery * 0.3f +
//            storage * 0.2f +
//            memory  * 0.2f +
//            cpu     * 0.15f +
//            temp    * 0.1f +
//            security* 0.05f
//
//_healthPercent.value = (score / 100f)

//fun runFullDiagnostics() {
//    val battery = 90
//    val storage = 75
//    val memory = 80
//    val cpu = 60
//    val temp = 65
//    val security = 95
//    val score =
//        battery * 0.3f +
//                storage * 0.2f +
//                memory  * 0.2f +
//                cpu     * 0.15f +
//                temp    * 0.1f +
//                security* 0.05f
//    _healthPercent.value = (score / 100f)
//}