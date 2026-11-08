package com.griffith.smoothdrive

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card


    @Composable
    fun PlaceholderScreen(title: String, modifier: Modifier = Modifier) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = title, style = MaterialTheme.typography.headlineMedium)
        }
    }

@Composable
fun DashboardScreen(
    onStartTrip: () -> Unit,
    onOpenReport: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Dashboard", style = MaterialTheme.typography.headlineMedium)

        val totalKm = sampleTrips.sumOf { it.distanceKm }
        val totalEvents = sampleTrips.sumOf { it.harshEvents }
        val eventsPer100Km = if (totalKm > 0) totalEvents / totalKm * 100 else 0.0

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("This week", style = MaterialTheme.typography.titleMedium)
                Text(
                    "${"%.1f".format(eventsPer100Km)} harsh events per 100 km",
                    style = MaterialTheme.typography.headlineSmall
                )
                Text("${sampleTrips.size} trips · ${"%.1f".format(totalKm)} km")
            }
        }

        Button(onClick = onStartTrip) { Text("Start Trip") }
        OutlinedButton(onClick = onOpenReport) { Text("Weekly Report") }
    }
}
