package com.levi.rappideliverer.controller

import com.levi.rappideliverer.dto.OrderWithItemsFoodDTO
import com.levi.rappideliverer.service.OrderService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/deliverer/order")
class OrderController(val service: OrderService) {

    fun create(orderWithItemsFoodDTO: OrderWithItemsFoodDTO) : OrderWithItemsFoodDTO {
        return service.createOrderWithItemsFood(orderWithItemsFoodDTO)
    }

}
