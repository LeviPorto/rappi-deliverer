package com.levi.rappideliverer.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class ItemFood (
        val foodId: Int,
        val orderId: Int,
        val comment: String,
        val quantity: Int,
        @Id val id: String? = null
)
