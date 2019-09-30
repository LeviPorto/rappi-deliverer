package com.levi.rappideliverer.controller

import com.levi.rappideliverer.domain.Order
import com.levi.rappideliverer.dto.OrderWithItemsFoodDTO
import com.levi.rappideliverer.service.OrderService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/deliverer/order")
class OrderController(val service: OrderService) {

    @PostMapping
    fun create(@Valid @RequestBody orderWithItemsFoodDTO: OrderWithItemsFoodDTO) : OrderWithItemsFoodDTO =
        service.createOrderWithItemsFood(orderWithItemsFoodDTO)


    @GetMapping("/user/{userId}")
    fun findDeliveredByUser(@PathVariable userId : Int) : List<Order> = service.retrieveDeliveredByUser(userId)


}
