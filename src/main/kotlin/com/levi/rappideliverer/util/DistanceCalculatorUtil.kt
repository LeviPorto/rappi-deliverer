package com.levi.rappideliverer.util

import kotlin.math.*

object DistanceCalculatorUtil {

    private const val EARTH_RADIUS = 6371

    fun calculateDistanceBetweenPoints(startLat: Double, startLong: Double,
                                       endLat: Double, endLong: Double): Double {
        var startLat = startLat
        var endLat = endLat
        val dLat = Math.toRadians(endLat - startLat)
        val dLong = Math.toRadians(endLong - startLong)

        startLat = Math.toRadians(startLat)
        endLat = Math.toRadians(endLat)

        val a = sin(dLat / 2).pow(2.0) + cos(startLat) * cos(endLat) * sin(dLong / 2).pow(2.0)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))

        return EARTH_RADIUS * c
    }
}
