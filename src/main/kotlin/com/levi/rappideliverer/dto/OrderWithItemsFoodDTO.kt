package com.levi.rappideliverer.dto

import com.levi.rappideliverer.domain.ItemFood
import com.levi.rappideliverer.domain.Order
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class OrderWithItemsFoodDTO (
        @NotNull val order: Order,
        @NotEmpty val itemFoods: List<ItemFood>

)
