package com.levi.rappideliverer.repository

import com.levi.rappideliverer.domain.ItemFood
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemFoodRepository : MongoRepository<ItemFood, String>
