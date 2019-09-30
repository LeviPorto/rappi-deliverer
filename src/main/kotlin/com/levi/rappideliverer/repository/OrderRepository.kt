package com.levi.rappideliverer.repository

import com.levi.rappideliverer.domain.Order
import com.levi.rappideliverer.domain.enumeration.DeliveryStatus
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : MongoRepository<Order, String> {

    fun findByUserIdAndAndDeliveryStatus(userId : Int, deliveryStatus: DeliveryStatus) : List<Order>

}
