package com.griffith.smoothdrive.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.griffith.smoothdrive.data.AppDatabase
import com.griffith.smoothdrive.data.Vehicle
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * Holds the vehicle list and handles add, edit and delete events from the screen.
 * It survives screen rotation, unlike state kept with remember (lecture 5).
 */
class VehiclesViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.get(application).vehicleDao()

    // Turns the database Flow into state the screen can observe.
    val vehicles: StateFlow<List<Vehicle>> = dao.getAll()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Database work runs in a coroutine, off the main thread (lecture 10).
    fun addVehicle(vehicle: Vehicle) {
        viewModelScope.launch { dao.insert(vehicle) }
    }

    fun updateVehicle(vehicle: Vehicle) {
        viewModelScope.launch { dao.update(vehicle) }
    }

    fun deleteVehicle(vehicle: Vehicle) {
        viewModelScope.launch { dao.delete(vehicle) }
    }
}