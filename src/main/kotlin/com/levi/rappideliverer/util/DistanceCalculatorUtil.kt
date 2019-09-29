package com.levi.rappideliverer.util

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

        val a = Math.pow(Math.sin(dLat / 2), 2.0) + Math.cos(startLat) * Math.cos(endLat) * Math.pow(Math.sin(dLong / 2), 2.0)
        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))

        return EARTH_RADIUS * c
    }
}
