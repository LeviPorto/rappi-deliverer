package com.levi.rappideliverer.repository

import com.levi.rappideliverer.domain.Order
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : MongoRepository<Order, String>
