package com.levi.rappideliverer.service

import com.levi.rappideliverer.api.ManagerApi
import com.levi.rappideliverer.dto.CoordinateDTO
import com.levi.rappideliverer.dto.RestaurantDTO
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class RestaurantService(val managerApi: ManagerApi) {

    @Cacheable(value = ["RESTAURANT_BY_DELIVERY_MAN_ID"], key = "{#coordinateDTO.deliveryManId}", unless = "#result == null")
    fun retrieveByDeliveryMan(coordinateDTO : CoordinateDTO) : RestaurantDTO {
        return managerApi.retrieveByDeliveryMan(coordinateDTO.deliveryManId)
    }

}
