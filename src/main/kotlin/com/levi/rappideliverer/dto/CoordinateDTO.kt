package com.levi.rappideliverer.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class CoordinateDTO @JsonCreator constructor(@JsonProperty("latitude") val latitude: Double,
                                                  @JsonProperty("longitude") val longitude: Double,
                                                  @JsonProperty("deliveryManId") val deliveryManId: Int,
                                                  @JsonProperty("orderId") val orderId: String)
