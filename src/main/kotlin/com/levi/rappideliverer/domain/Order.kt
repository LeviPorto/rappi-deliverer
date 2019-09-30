package com.levi.rappideliverer.domain

import com.levi.rappideliverer.domain.enumeration.DeliveryStatus
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.index.CompoundIndexes
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant
import javax.validation.constraints.NotNull

@Document
@CompoundIndexes(CompoundIndex(name = "restaurant_deliveryStatus", def = "{'restaurantId' : 1, 'deliveryStatus': 1}"))
data class Order (
        @NotNull val userId: Int,
        @NotNull val restaurantId: Int,
        val deliveryDate: Instant,
        var deliveryStatus: DeliveryStatus,
        @NotNull val deliveryAddress : String,
        @Id val id: String? = null
)
