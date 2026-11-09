package com.griffith.smoothdrive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.griffith.smoothdrive.ui.theme.SmoothDriveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmoothDriveTheme {
                SmoothDriveApp()
            }
        }
    }
}

/* This is the Main activity file, the apps entry point. This file applies the green theme and hands the whole
screen to the smoothDriveApp.
*/