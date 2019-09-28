package com.levi.rappideliverer.service

import com.levi.rappideliverer.domain.Order
import com.levi.rappideliverer.dto.OrderWithItemsFoodDTO
import com.levi.rappideliverer.publisher.RatingPublisher
import com.levi.rappideliverer.repository.OrderRepository
import org.springframework.stereotype.Service

@Service
class OrderService(val repository: OrderRepository, val itemFoodService: ItemFoodService,
                   val orderPublisher: RatingPublisher) {

    fun create(order : Order) : Order {
       return repository.save(order)
    }

    fun createOrderWithItemsFood(orderWithItemsFoodDTO : OrderWithItemsFoodDTO) : OrderWithItemsFoodDTO {
        val createdOrderWithItemsFoodDTO = OrderWithItemsFoodDTO(create(orderWithItemsFoodDTO.order),
                itemFoodService.batchCreate(orderWithItemsFoodDTO.itemFoods))
        orderPublisher.sendRatingToTopic(createdOrderWithItemsFoodDTO.order)
        return createdOrderWithItemsFoodDTO
    }

}
