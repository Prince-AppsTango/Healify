package com.app.healify.viewmodel

import androidx.lifecycle.ViewModel
import com.app.healify.helpers.BatteryHelper

class PhoneHealthViewModel(private val batteryHelper: BatteryHelper) : ViewModel() {
    fun getBatteryHealth(): String {
        return batteryHelper.getBatteryHealth()
    }
}