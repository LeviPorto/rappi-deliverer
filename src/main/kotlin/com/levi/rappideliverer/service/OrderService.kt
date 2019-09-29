package com.levi.rappideliverer.service

import com.levi.rappideliverer.domain.Order
import com.levi.rappideliverer.domain.enumeration.DeliveryStatus
import com.levi.rappideliverer.dto.OrderWithItemsFoodDTO
import com.levi.rappideliverer.publisher.OrderPublisher
import com.levi.rappideliverer.repository.OrderRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class OrderService(val repository: OrderRepository, val itemFoodService: ItemFoodService,
                   val orderPublisher: OrderPublisher) {

    fun create(order : Order) : Order {
       return repository.save(order)
    }

    fun createOrderWithItemsFood(orderWithItemsFoodDTO : OrderWithItemsFoodDTO) : OrderWithItemsFoodDTO {
        val createdOrderWithItemsFoodDTO = OrderWithItemsFoodDTO(create(orderWithItemsFoodDTO.order),
                itemFoodService.batchCreate(orderWithItemsFoodDTO.itemFoods))
        orderPublisher.sendOrderToTopic(createdOrderWithItemsFoodDTO.order)
        return createdOrderWithItemsFoodDTO
    }

    @Cacheable(value = ["ORDER_BY_ID"], key = "{#id}", unless = "#result == null")
    fun retrieveById(id : String) : Order {
        return repository.findById(id).get()
    }

    fun retrieveDeliveredByUser(userId : Int) : List<Order> {
        return repository.findByUserIdAndAndDeliveryStatus(userId, DeliveryStatus.DELIVERED)
    }

}
