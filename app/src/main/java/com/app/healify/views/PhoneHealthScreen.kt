package com.app.healify.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.healify.helpers.BatteryHelper
import com.app.healify.shared.HealthProgressCard
import com.app.healify.utils.PhoneHealthVMFactory
import com.app.healify.utils.SafeArea
import com.app.healify.viewmodel.PhoneHealthViewModel
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.healify.R
import com.app.healify.shared.CategoryCard

@Composable
fun PhoneHealthScreen() {
    val context = LocalContext.current

    val gradientColors = listOf(
        Color(0xFF7ED957),
        Color(0xFF3AB0A2)
    )
    val viewModel: PhoneHealthViewModel = viewModel(
        factory = PhoneHealthVMFactory(BatteryHelper(context))
    )
    val healthPercent  =  viewModel.healthPercent.collectAsState()
    val phoneHealthStatus = viewModel.hasRunDiagnostics.collectAsState()

    SafeArea {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState())
                .background(color = Color.White)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                HealthProgressCard(percent = healthPercent.value)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(modifier = Modifier.padding(horizontal = 15.dp)) {
                Row(modifier = Modifier.fillMaxWidth()){
                    CategoryCard(
                        text = "Battery",
                        subTitle = if(phoneHealthStatus.value.firstOrNull()?.data?.percentage != null) "${phoneHealthStatus.value.firstOrNull()?.data?.percentage}% ${phoneHealthStatus.value.firstOrNull()?.data?.status?.toString()}" else "N/A",
                        imageId = R.drawable.battery,
                        color = when(phoneHealthStatus.value.firstOrNull()?.data?.status) {
                            "Good" -> Color(0xFF4CAF50)
                            "Average" -> Color(0xFFFFC107)
                            "Poor" -> Color(0xFFFF5722)
                            "Critical" -> Color(0xFFF44336)
                            else -> Color.Gray
                        },
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    CategoryCard(
                        text = "Storage",
                        imageId = R.drawable.database,
                        subTitle = "${ phoneHealthStatus.value.firstOrNull()?.storageHealthModel?.freeGB?.toInt() ?: 0}GB/${ phoneHealthStatus.value.firstOrNull()?.storageHealthModel?.totalGB?.toInt() ?: 0}GB",
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(modifier = Modifier.fillMaxWidth()){
                    CategoryCard(
                        text = "RAM",
                        imageId = R.drawable.ram,
                        subTitle = if(phoneHealthStatus.value.firstOrNull()?.ramInfo?.freeGB != null)  "Free: ${phoneHealthStatus.value.firstOrNull()?.ramInfo?.freeGB?.toInt() } GB"  else "N/A",
                        color = if ((phoneHealthStatus.value.firstOrNull()?.ramInfo?.freeGB ?: 0.0) >= 2.0) Color.Black else Color.Gray,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    CategoryCard(
                        text = "Cores",
                        imageId = R.drawable.cpu,
                        subTitle = if( phoneHealthStatus.value.firstOrNull()?.cpuInfo?.cores != null) "${ phoneHealthStatus.value.firstOrNull()?.cpuInfo?.cores}" else "N/A",
                        color =  if( phoneHealthStatus.value.firstOrNull()?.cpuInfo?.cores != null) Color.Black else Color.Gray,
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(modifier = Modifier.fillMaxWidth()){
                    CategoryCard(
                        text = "Camera",
                        imageId = R.drawable.camera,
                        subTitle = if(phoneHealthStatus.value.firstOrNull()?.cameraInfo?.isAvailable == true)
                            "${phoneHealthStatus.value.firstOrNull()?.cameraInfo?.numberOfCameras} Camera(s)"
                        else "Not Available",
                        color = if(phoneHealthStatus.value.firstOrNull()?.cameraInfo?.isAvailable == true) Color(0xFF4CAF50) else Color(0xFFFF5722),
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    CategoryCard(
                        text = "Microphone",
                        imageId = R.drawable.microphone,
                        subTitle = if(phoneHealthStatus.value.firstOrNull()?.microphoneInfo?.isAvailable == true)
                            "Working"
                        else "Not Available",
                        color = if(phoneHealthStatus.value.firstOrNull()?.microphoneInfo?.isAvailable == true) Color(0xFF4CAF50) else Color(0xFFFF5722),
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(modifier = Modifier.fillMaxWidth()){
                    CategoryCard(
                        text = "Network",
                        imageId = R.drawable.network,
                        subTitle = if(phoneHealthStatus.value.firstOrNull()?.networkInfo?.isConnected == true)
                            "${phoneHealthStatus.value.firstOrNull()?.networkInfo?.connectionType} ${phoneHealthStatus.value.firstOrNull()?.networkInfo?.linkSpeedMbps}Mbps"
                        else "Not Connected",
                        color = if(phoneHealthStatus.value.firstOrNull()?.networkInfo?.isConnected == true) Color(0xFF4CAF50) else Color(0xFFFF5722),
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    CategoryCard(
                        text = "Sensors",
                        imageId = R.drawable.sensor,
                        subTitle = if(phoneHealthStatus.value.firstOrNull()?.sensorInfo?.totalSensors != null && phoneHealthStatus.value.firstOrNull()?.sensorInfo?.totalSensors!! > 0)
                            "${phoneHealthStatus.value.firstOrNull()?.sensorInfo?.totalSensors} Sensors"
                        else "N/A",
                        color = if((phoneHealthStatus.value.firstOrNull()?.sensorInfo?.totalSensors ?: 0) > 0) Color(0xFF4CAF50) else Color.Gray,
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(modifier = Modifier.fillMaxWidth()){
                    CategoryCard(
                        text = "Display",
                        imageId = R.drawable.display,
                        subTitle = if(phoneHealthStatus.value.firstOrNull()?.displayInfo?.screenSizeInches != null)
                            "${"%.1f".format(phoneHealthStatus.value.firstOrNull()?.displayInfo?.screenSizeInches)}\" ${phoneHealthStatus.value.firstOrNull()?.displayInfo?.refreshRate?.toInt()}Hz"
                        else "N/A",
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    CategoryCard(
                        text = "Bluetooth",
                        imageId = R.drawable.bluetooth,
                        subTitle = if(phoneHealthStatus.value.firstOrNull()?.bluetoothInfo?.isSupported == true)
                            if(phoneHealthStatus.value.firstOrNull()?.bluetoothInfo?.isEnabled == true) "Enabled" else "Disabled"
                        else "Not Supported",
                        color = when {
                            phoneHealthStatus.value.firstOrNull()?.bluetoothInfo?.isEnabled == true -> Color(0xFF4CAF50)
                            phoneHealthStatus.value.firstOrNull()?.bluetoothInfo?.isSupported == true -> Color(0xFFFFC107)
                            else -> Color(0xFFFF5722)
                        },
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(modifier = Modifier.fillMaxWidth()){
                    CategoryCard(
                        text = "System",
                        imageId = R.drawable.system,
                        subTitle = if(phoneHealthStatus.value.firstOrNull()?.systemInfo?.deviceBrand != null)
                            "${phoneHealthStatus.value.firstOrNull()?.systemInfo?.deviceBrand} Android ${phoneHealthStatus.value.firstOrNull()?.systemInfo?.androidVersion}"
                        else "N/A",
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    CategoryCard(
                        text = "NFC",
                        imageId = R.drawable.nfc,
                        subTitle = if(phoneHealthStatus.value.firstOrNull()?.nfcInfo?.isSupported == true)
                            if(phoneHealthStatus.value.firstOrNull()?.nfcInfo?.isEnabled == true) "Enabled" else "Disabled"
                        else "Not Supported",
                        color = when {
                            phoneHealthStatus.value.firstOrNull()?.nfcInfo?.isEnabled == true -> Color(0xFF4CAF50)
                            phoneHealthStatus.value.firstOrNull()?.nfcInfo?.isSupported == true -> Color(0xFFFFC107)
                            else -> Color(0xFFFF5722)
                        },
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(modifier = Modifier.fillMaxWidth()){
                    CategoryCard(
                        text = "GPS",
                        imageId = R.drawable.gps,
                        subTitle = if(phoneHealthStatus.value.firstOrNull()?.gpsInfo?.isSupported == true)
                            if(phoneHealthStatus.value.firstOrNull()?.gpsInfo?.isEnabled == true) "Enabled" else "Disabled"
                        else "Not Supported",
                        color = when {
                            phoneHealthStatus.value.firstOrNull()?.gpsInfo?.isEnabled == true -> Color(0xFF4CAF50)
                            phoneHealthStatus.value.firstOrNull()?.gpsInfo?.isSupported == true -> Color(0xFFFFC107)
                            else -> Color(0xFFFF5722)
                        },
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    CategoryCard(
                        text = "Speaker",
                        imageId = R.drawable.speaker,
                        subTitle = if(phoneHealthStatus.value.firstOrNull()?.speakerInfo?.isAvailable == true)
                            "Vol: ${phoneHealthStatus.value.firstOrNull()?.speakerInfo?.currentVolume}/${phoneHealthStatus.value.firstOrNull()?.speakerInfo?.maxVolume}"
                        else "N/A",
                        color = if(phoneHealthStatus.value.firstOrNull()?.speakerInfo?.isAvailable == true) Color(0xFF4CAF50) else Color.Gray,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.height(10.dp))
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.95f)
                        .background(
                            brush = Brush.linearGradient(
                                colors = gradientColors,
                                start = Offset(0f, 0f),
                                end = Offset(1000f, 500f)
                            ),
                            shape = MaterialTheme.shapes.large
                        )
                        .clickable {
                            viewModel.runFullDiagnostics(context)
                        }
                        .padding(5.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "RUN FULL DIAGNOSTICS",
                        modifier = Modifier.padding(15.dp),
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W500
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}
