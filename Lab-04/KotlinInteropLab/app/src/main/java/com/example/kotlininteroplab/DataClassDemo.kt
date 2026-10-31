package com.example.kotlininteroplab

fun demonstrateDataClass(): String {
    val original = DeviceReading("Sensor-A", 15, "Sensor")
    val calibrated = original.copy(batteryPercent = 25)
    val (name, battery, category) = calibrated
    return "$name | $battery% | $category | equal=${original == calibrated}"
}