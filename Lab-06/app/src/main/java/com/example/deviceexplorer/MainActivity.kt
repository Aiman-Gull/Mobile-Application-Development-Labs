package com.example.deviceexplorer

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.LifecycleEventObserver
import com.example.deviceexplorer.ui.theme.DeviceExplorerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(LifecycleEventObserver { _, event ->
            Log.d("Lab06Activity", event.toString())
        })
        enableEdgeToEdge()
        setContent {
            DeviceExplorerTheme {
                DeviceExplorerApp()
            }
        }
    }
}