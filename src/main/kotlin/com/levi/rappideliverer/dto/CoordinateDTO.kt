package com.levi.rappideliverer.dto

import java.time.Instant

data class CoordinateDTO (
        val latitude : Double,
        val longitude : Double,
        val date : Instant,
        val deliveryManId : Int,
        val orderId : String
)
