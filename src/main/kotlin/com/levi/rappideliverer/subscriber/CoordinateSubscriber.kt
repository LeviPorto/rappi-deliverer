package com.levi.rappideliverer.subscriber

import com.levi.rappideliverer.dispatcher.CoordinateDispatcher
import com.levi.rappideliverer.dto.CoordinateDTO
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class CoordinateSubscriber(private val coordinateDispatcher: CoordinateDispatcher) {

    @KafkaListener(topics = ["COORDINATE_EVENT_SOURCING"], groupId = "1234", containerFactory = "kafkaListenerContainerFactory")
    fun processCoordinateEventSourcing(@Payload coordinateDTO: CoordinateDTO) {
        coordinateDispatcher.notifyCoordinateCreateListeners(coordinateDTO)
    }

}
