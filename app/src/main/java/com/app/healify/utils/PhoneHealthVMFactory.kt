package com.app.healify.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.healify.helpers.BatteryHelper
import com.app.healify.viewmodel.PhoneHealthViewModel

class PhoneHealthVMFactory(
    private val batteryHelper: BatteryHelper,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PhoneHealthViewModel(batteryHelper) as T
    }
}
