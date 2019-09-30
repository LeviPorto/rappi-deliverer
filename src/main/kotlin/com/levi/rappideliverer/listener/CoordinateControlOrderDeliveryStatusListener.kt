package com.levi.rappideliverer.listener

import com.levi.rappideliverer.domain.enumeration.DeliveryStatus
import com.levi.rappideliverer.dto.CoordinateDTO
import com.levi.rappideliverer.dto.RestaurantDTO
import com.levi.rappideliverer.service.OrderService
import com.levi.rappideliverer.service.RestaurantService
import com.levi.rappideliverer.util.DistanceCalculatorUtil.calculateDistanceBetweenPoints
import org.springframework.stereotype.Component

@Component
class CoordinateControlOrderDeliveryStatusListener(val orderService : OrderService,
                                                   val restaurantService: RestaurantService) : CoordinateCreatedListener {
    companion object {
        const val DISTANCE_CONSIDERED_DELIVERED = 200
    }

    override fun coordinateWasCreated(coordinateDTO: CoordinateDTO) {
        val order = orderService.retrieveById(coordinateDTO.orderId)
        val restaurant = restaurantService.retrieveByDeliveryMan(coordinateDTO)

        if(order.deliveryStatus == DeliveryStatus.PENDING) {
            order.deliveryStatus = DeliveryStatus.PROGRESS
        } else {
            if(order.deliveryStatus == DeliveryStatus.PROGRESS) {
                if (arrivedAtDestination(coordinateDTO, restaurant)) {
                    order.deliveryStatus = DeliveryStatus.DELIVERED
                }
            }
        }
        orderService.create(order)
    }

    private fun arrivedAtDestination(coordinateDTO: CoordinateDTO, restaurant: RestaurantDTO) =
            calculateDistanceBetweenPoints(coordinateDTO.latitude, coordinateDTO.longitude!!,
                    restaurant.latitude, restaurant.longitude) < DISTANCE_CONSIDERED_DELIVERED

}
