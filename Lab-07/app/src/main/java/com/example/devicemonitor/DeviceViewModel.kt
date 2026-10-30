package com.example.devicemonitor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import java.io.IOException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DeviceViewModel(
    private val loader: suspend (Boolean) -> List<Device> = ::loadDemoDevices
) : ViewModel() {

    private val _uiState = MutableStateFlow<DeviceUiState>(DeviceUiState.Loading)
    val uiState: StateFlow<DeviceUiState> = _uiState.asStateFlow()

    private var request: Job? = null

    init {
        refresh()
    }

    fun refresh(fail: Boolean = false) {
        if (request?.isActive == true) return
        _uiState.value = DeviceUiState.Loading
        request = viewModelScope.launch {
            try {
                val records = loader(fail)
                _uiState.value = DeviceUiState.Content(records.toList())
            } catch (cancelled: CancellationException) {
                throw cancelled
            } catch (failure: IOException) {
                _uiState.value = DeviceUiState.Error
            }
        }
    }
}