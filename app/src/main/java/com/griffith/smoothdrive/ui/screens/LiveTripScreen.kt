package com.griffith.smoothdrive.ui.screens

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.griffith.smoothdrive.data.SettingsStore
import com.griffith.smoothdrive.sensors.HarshEventDetector
import kotlin.math.sqrt

/**
 * Records a trip: while recording, listens to the linear acceleration sensor and
 * counts harsh events. The listener is registered when recording starts and
 * unregistered when it stops or the screen is left, to save battery (lecture 11).
 */
@Composable
fun LiveTripScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val threshold = remember { SettingsStore(context).load().sensitivity }

    var isRecording by remember { mutableStateOf(false) }
    var eventCount by remember { mutableIntStateOf(0) }
    var currentStrength by remember { mutableFloatStateOf(0f) }

    val sensorManager = remember {
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }
    // Linear acceleration is a virtual sensor that leaves gravity out (lecture 7).
    val sensor = remember {
        sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)
    }

    // Runs again each time isRecording changes. onDispose cleans up the listener.
    DisposableEffect(isRecording) {
        if (!isRecording || sensor == null) {
            onDispose { }
        } else {
            val detector = HarshEventDetector(threshold)
            val listener = object : SensorEventListener {
                override fun onSensorChanged(event: SensorEvent) {
                    val x = event.values[0]
                    val y = event.values[1]
                    val z = event.values[2]
                    currentStrength = sqrt(x * x + y * y + z * z)
                    // SensorEvent timestamps are in nanoseconds, so convert to milliseconds.
                    if (detector.isHarshEvent(x, y, z, event.timestamp / 1_000_000L)) {
                        eventCount++
                    }
                }

                override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
            }
            sensorManager.registerListener(
                listener, sensor, SensorManager.SENSOR_DELAY_UI
            )
            onDispose { sensorManager.unregisterListener(listener) }
        }
    }

    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Live Trip", style = MaterialTheme.typography.headlineMedium)

        if (sensor == null) {
            Text("This device has no linear acceleration sensor.")
        } else {
            Text(
                "$eventCount",
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Text("harsh events")
            Text("Current strength: ${"%.1f".format(currentStrength)} m/s²")
            Text("Threshold: ${"%.1f".format(threshold)} m/s²")

            Button(onClick = {
                if (!isRecording) {
                    eventCount = 0
                    currentStrength = 0f
                }
                isRecording = !isRecording
            }) {
                Text(if (isRecording) "Stop" else "Start")
            }
        }
    }
}