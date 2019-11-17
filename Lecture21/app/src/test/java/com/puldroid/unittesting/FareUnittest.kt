package com.puldroid.unittesting


import org.junit.Assert.assertEquals
import org.junit.Test

class FareUnittest {

    @Test
    fun calcFate_for_0km_0min() {
        assertEquals(50, MainActivity.calcFare(0, 0))
    }

    @Test
    fun calcFate_for_5km_10min() {
        assertEquals(80, MainActivity.calcFare(5, 10))
    }

    @Test
    fun calcFate_for_2km_30min() {
        assertEquals(90, MainActivity.calcFare(2, 30))
    }

    @Test
    fun calcFate_for_50km_300min() {
        assertEquals(1110, MainActivity.calcFare(50, 300))
    }
}