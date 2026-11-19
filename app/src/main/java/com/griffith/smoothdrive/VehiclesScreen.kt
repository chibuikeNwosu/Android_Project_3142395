package com.griffith.smoothdrive

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.griffith.smoothdrive.data.Vehicle

/**
 * Lists the vehicles stored in the database. The list updates by itself whenever
 * the table changes.
 */
@Composable
fun VehiclesScreen(
    modifier: Modifier = Modifier,
    viewModel: VehiclesViewModel = viewModel()
) {
    val vehicles by viewModel.vehicles.collectAsState()

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Vehicles", style = MaterialTheme.typography.headlineMedium)

        // TEMPORARY: proves the database works. Replaced by a proper form next step.
        Button(onClick = {
            viewModel.addVehicle(
                Vehicle(
                    name = "Test van",
                    fuelType = "Diesel",
                    baselineLitresPer100Km = 8.5,
                    co2KgPerLitre = 0.0
                )
            )
        }) { Text("Add test vehicle") }

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(vehicles, key = { it.id }) { vehicle ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(vehicle.name, style = MaterialTheme.typography.titleMedium)
                        Text("${vehicle.fuelType} · ${vehicle.baselineLitresPer100Km} l/100 km")
                    }
                }
            }
        }
    }
}