package com.griffith.smoothdrive

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Update
import kotlinx.coroutines.flow.Flow

/**
 * The database actions for vehicles. Room writes the SQL behind each function.
 */
@Dao
interface VehicleDao {

    @Query("SELECT * FROM vehicles ORDER BY name")
    fun getAll(): Flow<List<Vehicle>>

    @Insert
    suspend fun insert(vehicle: Vehicle)

    @Update
    suspend fun update(vehicle: Vehicle)

    @Delete
    suspend fun delete(vehicle: Vehicle)
}