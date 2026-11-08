package com.griffith.smoothdrive

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

private const val TIPS_URL = "https://www.seai.ie/about/tools/energyacademy"

@Composable
fun WeeklyReportScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    val totalKm = sampleTrips.sumOf { it.distanceKm }
    val totalEvents = sampleTrips.sumOf { it.harshEvents }
    val eventsPer100Km = if (totalKm > 0) totalEvents / totalKm * 100 else 0.0

    val reportText = "SmoothDrive weekly report\n" +
            "Trips: ${sampleTrips.size}\n" +
            "Distance: ${"%.1f".format(totalKm)} km\n" +
            "Harsh events: $totalEvents\n" +
            "Events per 100 km: ${"%.1f".format(eventsPer100Km)}"

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Weekly Report", style = MaterialTheme.typography.headlineMedium)
        Text("Trips: ${sampleTrips.size}")
        Text("Distance: ${"%.1f".format(totalKm)} km")
        Text("Harsh events: $totalEvents")
        Text("Events per 100 km: ${"%.1f".format(eventsPer100Km)}")

        Button(onClick = {
            val sendIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, "SmoothDrive weekly report")
                putExtra(Intent.EXTRA_TEXT, reportText)
            }
            try {
                context.startActivity(Intent.createChooser(sendIntent, "Share report"))
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(context, "No app available to share", Toast.LENGTH_SHORT).show()
            }
        }) { Text("Share report") }

        OutlinedButton(onClick = {
            try {
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(TIPS_URL)))
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(context, "No browser available", Toast.LENGTH_SHORT).show()
            }
        }) { Text("Eco-driving tips (SEAI)") }
    }
}