package com.levi.rappideliverer.domain

import com.levi.rappideliverer.domain.enumeration.DeliveryStatus
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document
data class Order (
        val userId: Int,
        val restaurantId: Int,
        val deliveryDate: Instant,
        var deliveryStatus: DeliveryStatus,
        val deliveryAddress : String,
        @Id val id: String? = null
)
