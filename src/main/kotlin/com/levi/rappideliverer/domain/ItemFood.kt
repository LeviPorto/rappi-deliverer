package com.levi.rappideliverer.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

@Document
data class ItemFood (
        @NotNull val foodId: Int,
        @NotNull val orderId: Int,
        val comment: String,
        @NotNull @Positive val quantity: Int,
        @Id val id: String? = null
)
