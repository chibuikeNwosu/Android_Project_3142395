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

class Screens {
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
            Button(onClick = onStartTrip) { Text("Start Trip") }
            OutlinedButton(onClick = onOpenReport) { Text("Weekly Report") }
        }
    }
}