package com.levi.rappideliverer.dto

import com.levi.rappideliverer.domain.ItemFood
import com.levi.rappideliverer.domain.Order

data class OrderWithItemsFoodDTO (
        val order: Order,
        val itemFoods: List<ItemFood>

)
