package com.example.devicemanager

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class EditDeviceActivity : AppCompatActivity() {

    private val TAG = "EditDeviceActivityLC"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        setContentView(R.layout.activity_edit_device)

        // 1. Find the views
        val nameInput = findViewById<EditText>(R.id.etDeviceName)
        val batteryInput = findViewById<EditText>(R.id.etBattery)
        val saveButton = findViewById<Button>(R.id.btnSave)
        val cancelButton = findViewById<Button>(R.id.btnCancel)

        // 2. Read the extras from the Intent
        val receivedName = intent.getStringExtra(MainActivity.EXTRA_DEVICE_NAME)
        val receivedBattery = intent.getIntExtra(MainActivity.EXTRA_BATTERY, 0)

        // 3. Populate the input fields
        nameInput.setText(receivedName ?: "")
        batteryInput.setText(receivedBattery.toString())

        // 4. Set up the SAVE button
        saveButton.setOnClickListener {
            val newName = nameInput.text.toString().trim()
            val newBatteryText = batteryInput.text.toString().trim()

            // Validate Name
            if (newName.isEmpty()) {
                nameInput.error = getString(R.string.error_invalid_name)
                return@setOnClickListener
            }

            // Validate Battery (must be a number and between 0-100)
            val newBattery = newBatteryText.toIntOrNull()
            if (newBattery == null || newBattery < 0 || newBattery > 100) {
                batteryInput.error = getString(R.string.error_invalid_battery)
                return@setOnClickListener
            }

            // If validation passes, prepare the result Intent
            val resultIntent = Intent().apply {
                putExtra(MainActivity.EXTRA_DEVICE_NAME, newName)
                putExtra(MainActivity.EXTRA_BATTERY, newBattery)
            }

            // Set the result and finish the activity
            setResult(RESULT_OK, resultIntent)
            finish()
        }

        // 5. Set up the CANCEL button
        cancelButton.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }

    // Task 6: Lifecycle Logs
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }
}