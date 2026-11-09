package com.griffith.smoothdrive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.griffith.smoothdrive.ui.theme.SmoothDriveTheme

/*
This is a second screen, opened with an explicit intent from Trip History. It reads the trip's details from the intent extras and shows them and it is declared in the manifest to prevent
the app from crashing
*/
class TripDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val title = intent.getStringExtra(EXTRA_TITLE) ?: "Trip"
        val date = intent.getStringExtra(EXTRA_DATE) ?: ""
        val distance = intent.getDoubleExtra(EXTRA_DISTANCE, 0.0)
        val events = intent.getIntExtra(EXTRA_EVENTS, 0)

        setContent {
            SmoothDriveTheme {
                TripDetailScreen(
                    title = title,
                    date = date,
                    distanceKm = distance,
                    harshEvents = events,
                    onBack = { finish() }
                )
            }
        }
    }

    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_DATE = "extra_date"
        const val EXTRA_DISTANCE = "extra_distance"
        const val EXTRA_EVENTS = "extra_events"
    }
}

@Composable
fun TripDetailScreen(
    title: String,
    date: String,
    distanceKm: Double,
    harshEvents: Int,
    onBack: () -> Unit
) {
    val eventsPer100Km = if (distanceKm > 0) harshEvents / distanceKm * 100 else 0.0

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(title, style = MaterialTheme.typography.headlineMedium)
            Text(date)
            Text("Distance: $distanceKm km")
            Text("Harsh events: $harshEvents")
            Text("Events per 100 km: ${"%.1f".format(eventsPer100Km)}")
            Button(onClick = onBack) { Text("Back to history") }
        }
    }
}