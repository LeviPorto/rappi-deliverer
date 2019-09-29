package com.levi.rappideliverer.controller

import com.levi.rappideliverer.domain.Order
import com.levi.rappideliverer.dto.OrderWithItemsFoodDTO
import com.levi.rappideliverer.service.OrderService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/deliverer/order")
class OrderController(val service: OrderService) {

    @PostMapping
    fun create(@RequestBody orderWithItemsFoodDTO: OrderWithItemsFoodDTO) : OrderWithItemsFoodDTO {
        return service.createOrderWithItemsFood(orderWithItemsFoodDTO)
    }

    @GetMapping("/user/{userId}")
    fun findDeliveredByUser(@PathVariable userId : Int) : List<Order> {
        return service.retrieveDeliveredByUser(userId)
    }

}
