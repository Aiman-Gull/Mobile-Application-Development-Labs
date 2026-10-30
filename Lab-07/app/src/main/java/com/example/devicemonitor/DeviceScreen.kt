package com.example.devicemonitor

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DeviceRoute(vm: DeviceViewModel = viewModel()) {
    val state by vm.uiState.collectAsStateWithLifecycle()
    DeviceScreen(
        state = state,
        onRefresh = { vm.refresh() },
        onFail = { vm.refresh(fail = true) }
    )
}

@Composable
fun DeviceScreen(
    state: DeviceUiState,
    onRefresh: () -> Unit,
    onFail: () -> Unit
) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                stringResource(R.string.monitor_title),
                style = MaterialTheme.typography.headlineSmall
            )
            when (state) {
                DeviceUiState.Loading -> {
                    CircularProgressIndicator()
                    Text(
                        stringResource(R.string.loading_devices),
                        modifier = Modifier.semantics {
                            liveRegion = LiveRegionMode.Polite
                        }
                    )
                }
                is DeviceUiState.Content -> {
                    if (state.devices.isEmpty()) {
                        Text(stringResource(R.string.empty_devices))
                    }
                    state.devices.forEach { device ->
                        Text(
                            stringResource(R.string.device_row, device.name, device.battery)
                        )
                    }
                    Button(onClick = onRefresh) {
                        Text(stringResource(R.string.refresh_devices))
                    }
                    OutlinedButton(onClick = onFail) {
                        Text(stringResource(R.string.simulate_error))
                    }
                }
                DeviceUiState.Error -> {
                    Text(
                        stringResource(R.string.load_error),
                        modifier = Modifier.semantics {
                            liveRegion = LiveRegionMode.Polite
                        }
                    )
                    Button(onClick = onRefresh) {
                        Text(stringResource(R.string.retry_devices))
                    }
                }
            }
        }
    }
}