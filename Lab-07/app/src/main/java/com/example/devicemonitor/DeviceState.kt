package com.example.devicemonitor

import java.io.IOException
import kotlinx.coroutines.delay

data class Device(val id: String, val name: String, val battery: Int)

sealed interface DeviceUiState {
    data object Loading : DeviceUiState
    data class Content(val devices: List<Device>) : DeviceUiState
    data object Error : DeviceUiState
}

suspend fun loadDemoDevices(fail: Boolean): List<Device> {
    //android.util.Log.d("DeviceLoad", "load started")
    delay(1000)
    if (fail) throw IOException("Simulated data failure")
    return listOf(
        Device("d1", "Sensor-A", 80),
        Device("d2", "Camera-B", 45),
        Device("d3", "Gateway-C", 20)
    )
}