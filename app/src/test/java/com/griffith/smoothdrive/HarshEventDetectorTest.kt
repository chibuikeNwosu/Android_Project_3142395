package com.griffith.smoothdrive.sensors

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class HarshEventDetectorTest {

    @Test
    fun readingBelowThreshold_isNotHarsh() {
        val detector = HarshEventDetector(1.5f)
        assertFalse(detector.isHarshEvent(0.5f, 0.5f, 0.5f, 10_000L))
    }

    @Test
    fun readingAboveThreshold_isHarsh() {
        val detector = HarshEventDetector(1.5f)
        assertTrue(detector.isHarshEvent(2.0f, 0f, 0f, 10_000L))
    }

    @Test
    fun secondReadingInsideCooldown_isNotCounted() {
        val detector = HarshEventDetector(1.5f)
        assertTrue(detector.isHarshEvent(2.0f, 0f, 0f, 10_000L))
        assertFalse(detector.isHarshEvent(2.0f, 0f, 0f, 10_500L))
    }

    @Test
    fun readingAfterCooldown_isCountedAgain() {
        val detector = HarshEventDetector(1.5f)
        assertTrue(detector.isHarshEvent(2.0f, 0f, 0f, 10_000L))
        assertTrue(detector.isHarshEvent(2.0f, 0f, 0f, 12_500L))
    }
}