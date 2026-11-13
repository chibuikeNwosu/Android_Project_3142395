package com.griffith.smoothdrive

import androidx.room3.Entity
import androidx.room3.PrimaryKey

/**
 * One vehicle in the business's fleet. Room turns this class into a table called "vehicles".
 */
@Entity(tableName = "vehicles")
data class Vehicle(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val fuelType: String,
    val baselineLitresPer100Km: Double,
    val co2KgPerLitre: Double
)