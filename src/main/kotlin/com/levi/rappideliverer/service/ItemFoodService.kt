package com.levi.rappideliverer.service

import com.levi.rappideliverer.domain.ItemFood
import com.levi.rappideliverer.repository.ItemFoodRepository
import org.springframework.stereotype.Service

@Service
class ItemFoodService(val repository : ItemFoodRepository) {

    fun batchCreate(itemFoods : List<ItemFood>) : List<ItemFood> {
        return repository.saveAll(itemFoods)
    }

}
