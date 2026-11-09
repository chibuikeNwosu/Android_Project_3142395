package com.griffith.smoothdrive

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


/*
This file represents a reusable card showing the week's events per 100 km, trips and distance
it is used on the dashboard and the weekly report so the numbers can never disagree
*/
@Composable
fun WeeklySummaryCard(stats: WeeklyStats, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text("This week", style = MaterialTheme.typography.titleMedium)
            Text(
                "${"%.1f".format(stats.eventsPer100Km)} harsh events per 100 km",
                style = MaterialTheme.typography.headlineSmall
            )
            Text("${stats.trips} trips · ${"%.1f".format(stats.distanceKm)} km")
        }
    }
}