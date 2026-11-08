package com.griffith.smoothdrive

data class TripSummary(
    val id: Int,
    val title: String,
    val date: String,
    val distanceKm: Double,
    val harshEvents: Int
)

val sampleTrips = listOf(
    TripSummary(1, "Cork to Galway", "Tue 3rd July", 34.2, 3),
    TripSummary(2, "Car repair run", "Mon 5th August", 12.8, 1),
    TripSummary(3, "Parcel drop of", "Thu 20th September", 58.6, 6)
)
data class WeeklyStats(
    val trips: Int,
    val distanceKm: Double,
    val harshEvents: Int
) {
    val eventsPer100Km: Double
        get() = if (distanceKm > 0) harshEvents / distanceKm * 100 else 0.0
}

fun weeklyStatsOf(trips: List<TripSummary>) = WeeklyStats(
    trips = trips.size,
    distanceKm = trips.sumOf { it.distanceKm },
    harshEvents = trips.sumOf { it.harshEvents }
)