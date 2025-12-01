package com.app.healify.viewmodel

import androidx.lifecycle.ViewModel
import com.app.healify.helpers.BatteryHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PhoneHealthViewModel(private val batteryHelper: BatteryHelper) : ViewModel() {
    private val _healthPercent = MutableStateFlow(0f) // 0f..1f
    val healthPercent = _healthPercent.asStateFlow()
    fun runFullDiagnostics() {
        val battery = batteryHelper.getBatteryHealth()
        val score = battery * 0.3f
        _healthPercent.value = (score / 100f)
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