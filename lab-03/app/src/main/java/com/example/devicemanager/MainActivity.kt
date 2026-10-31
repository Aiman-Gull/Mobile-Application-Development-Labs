package com.example.devicemanager

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DEVICE_NAME = "com.example.devicemanager.DEVICE_NAME"
        const val EXTRA_BATTERY = "com.example.devicemanager.BATTERY"
    }

    private var deviceName = "Sensor-A"
    private var battery = 15

    private val editDeviceLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK && result.data != null) {
            val data = result.data!!
            val returnedName = data.getStringExtra(EXTRA_DEVICE_NAME)
            val returnedBattery = data.getIntExtra(EXTRA_BATTERY, battery)

            if (returnedName != null) {
                deviceName = returnedName
            }
            battery = returnedBattery
            updateSummary()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateSummary()

        val editButton = findViewById<Button>(R.id.btnEditDevice)
        editButton.setOnClickListener {
            val editIntent = Intent(this, EditDeviceActivity::class.java)
            editIntent.putExtra(EXTRA_DEVICE_NAME, deviceName)
            editIntent.putExtra(EXTRA_BATTERY, battery)
            editDeviceLauncher.launch(editIntent)
        }

        val docsButton = findViewById<Button>(R.id.btnOpenDocumentation)
        docsButton.setOnClickListener {
            val page = Uri.parse("https://developer.android.com")
            val webIntent = Intent(Intent.ACTION_VIEW, page)
            try {
                startActivity(webIntent)
            } catch (ex: ActivityNotFoundException) {
                Toast.makeText(this, "No application can open this link", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun updateSummary() {
        val nameView = findViewById<TextView>(R.id.tvDeviceName)
        val batteryView = findViewById<TextView>(R.id.tvBattery)

        nameView.text = getString(R.string.device_name_value, deviceName)
        batteryView.text = getString(R.string.battery_value, battery)
    }
}