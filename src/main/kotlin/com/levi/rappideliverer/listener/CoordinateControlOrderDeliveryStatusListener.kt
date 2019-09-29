package com.levi.rappideliverer.listener

import com.levi.rappideliverer.api.ManagerApi
import com.levi.rappideliverer.domain.enumeration.DeliveryStatus
import com.levi.rappideliverer.dto.CoordinateDTO
import com.levi.rappideliverer.service.OrderService
import com.levi.rappideliverer.util.DistanceCalculatorUtil.calculateDistanceBetweenPoints
import org.springframework.stereotype.Component

@Component
class CoordinateControlOrderDeliveryStatusListener(val orderService : OrderService,
                                                   val managerApi: ManagerApi) : CoordinateCreatedListener {
    companion object {
        const val DISTANCE_CONSIDERED_DELIVERED = 200
    }

    override fun coordinateWasCreated(coordinateDTO: CoordinateDTO) {
        var order = orderService.retrieveById(coordinateDTO.orderId)
        val restaurant = managerApi.retrieveByDeliveryMan(coordinateDTO.deliveryManId)

        if(order.deliveryStatus == DeliveryStatus.PENDING) {
            order.deliveryStatus = DeliveryStatus.PROGRESS
        } else {
            if(order.deliveryStatus == DeliveryStatus.PROGRESS) {
                if (calculateDistanceBetweenPoints(coordinateDTO.latitude, coordinateDTO.longitude,
                                restaurant.latitude, restaurant.longitude) < DISTANCE_CONSIDERED_DELIVERED) {
                    order.deliveryStatus = DeliveryStatus.DELIVERED
                }
            }
        }
        orderService.create(order)
    }

}
