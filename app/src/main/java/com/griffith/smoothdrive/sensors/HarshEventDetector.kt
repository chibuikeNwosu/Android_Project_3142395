package com.griffith.smoothdrive.sensors

import kotlin.math.sqrt

/*
 * Decides whether a reading from the linear acceleration sensor counts as a harsh
 * driving event. It is plain Kotlin with no Android code, so it can be unit tested.
 */
class HarshEventDetector(
    private val thresholdMs2: Float,
    private val cooldownMs: Long = 2000L
) {
    // Starts far enough back that the very first reading is never blocked by the cooldown.
    private var lastEventTimeMs = -cooldownMs

    /* Returns true if this reading is a new harsh event. */
    fun isHarshEvent(x: Float, y: Float, z: Float, timeMs: Long): Boolean {
        val magnitude = sqrt(x * x + y * y + z * z)
        if (magnitude < thresholdMs2) return false
        // One braking action produces many readings, so ignore repeats for a short time.
        if (timeMs - lastEventTimeMs < cooldownMs) return false
        lastEventTimeMs = timeMs
        return true
    }
}