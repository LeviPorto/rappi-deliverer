package com.levi.rappideliverer.api

import com.levi.rappideliverer.dto.RestaurantDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "\${feign.api.manager}")
@Component
interface ManagerApi {

    @RequestMapping(method = [RequestMethod.GET], value = ["/manager/restaurant/deliveryMan/{deliveryManId}"])
    fun retrieveByDeliveryMan(@PathVariable("deliveryManId") id: Int): RestaurantDTO

}
