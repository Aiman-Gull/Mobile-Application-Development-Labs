package com.example.devicemonitor

import java.io.IOException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DeviceViewModelTest {

    @get:Rule
    val main = MainDispatcherRule()

    @Test
    fun load_movesFromLoadingToContent() = runTest(main.dispatcher) {
        val records = listOf(Device("t1", "Test device", 65))
        val vm = DeviceViewModel { _ -> delay(100); records }

        assertEquals(DeviceUiState.Loading, vm.uiState.value)
        runCurrent()
        assertEquals(DeviceUiState.Loading, vm.uiState.value)
        advanceUntilIdle()
        assertEquals(DeviceUiState.Content(records), vm.uiState.value)
    }

    @Test
    fun retry_recoversFromError() = runTest(main.dispatcher) {
        var calls = 0
        val records = listOf(Device("t2", "Recovered device", 90))
        val vm = DeviceViewModel { _ ->
            calls++
            delay(100)
            if (calls == 1) throw IOException("Test failure")
            records
        }

        advanceUntilIdle()
        assertEquals(DeviceUiState.Error, vm.uiState.value)
        assertEquals(1, calls)

        vm.refresh()
        assertEquals(DeviceUiState.Loading, vm.uiState.value)
        advanceUntilIdle()
        assertEquals(DeviceUiState.Content(records), vm.uiState.value)
        assertEquals(2, calls)
    }

    @Test
    fun emptyList_isValidContent() = runTest(main.dispatcher) {
        val vm = DeviceViewModel { _ -> emptyList() }

        advanceUntilIdle()
        assertEquals(DeviceUiState.Content(emptyList()), vm.uiState.value)
    }

    @Test
    fun overlappingRefresh_isIgnored() = runTest(main.dispatcher) {
        var calls = 0
        val vm = DeviceViewModel { _ ->
            calls++
            delay(100)
            emptyList()
        }

        runCurrent()
        assertEquals(1, calls)
        vm.refresh()
        vm.refresh()
        runCurrent()
        assertEquals(1, calls)
        assertEquals(DeviceUiState.Loading, vm.uiState.value)

        advanceUntilIdle()
        assertEquals(1, calls)
        assertEquals(DeviceUiState.Content(emptyList()), vm.uiState.value)
    }
}