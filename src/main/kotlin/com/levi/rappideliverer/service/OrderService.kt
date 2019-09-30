package com.levi.rappideliverer.service

import com.levi.rappideliverer.domain.Order
import com.levi.rappideliverer.domain.enumeration.DeliveryStatus
import com.levi.rappideliverer.dto.OrderWithItemsFoodDTO
import com.levi.rappideliverer.publisher.OrderPublisher
import com.levi.rappideliverer.repository.OrderRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.Caching
import org.springframework.stereotype.Service

@Service
class OrderService(val repository: OrderRepository, val itemFoodService: ItemFoodService,
                   val orderPublisher: OrderPublisher) {

    @Caching(evict = [CacheEvict(value = ["ORDER_DELIVERED_BY_USER"], key = "{#order.userId}")])
    fun create(order : Order) : Order = repository.save(order)


    fun createOrderWithItemsFood(orderWithItemsFoodDTO : OrderWithItemsFoodDTO) : OrderWithItemsFoodDTO {
        val createdOrderWithItemsFoodDTO = OrderWithItemsFoodDTO(create(orderWithItemsFoodDTO.order),
                itemFoodService.batchCreate(orderWithItemsFoodDTO.itemFoods))
        orderPublisher.sendOrderToTopic(createdOrderWithItemsFoodDTO.order)
        return createdOrderWithItemsFoodDTO
    }

    @Cacheable(value = ["ORDER_BY_ID"], key = "{#id}", unless = "#result == null")
    fun retrieveById(id : String) : Order = repository.findById(id).get()


    @Cacheable(value = ["ORDER_DELIVERED_BY_USER"], key = "{#userId}", unless = "#result == null || #result.isEmpty()")
    fun retrieveDeliveredByUser(userId : Int) : List<Order> =
        repository.findByUserIdAndAndDeliveryStatus(userId, DeliveryStatus.DELIVERED)


}
