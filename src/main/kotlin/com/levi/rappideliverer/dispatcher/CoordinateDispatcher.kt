package com.levi.rappideliverer.dispatcher

import com.levi.rappideliverer.dto.CoordinateDTO
import com.levi.rappideliverer.listener.CoordinateCreatedListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CoordinateDispatcher(private val coordinateCreatedListeners: List<CoordinateCreatedListener>) {

    fun notifyCoordinateCreateListeners(coordinate: CoordinateDTO) {
        for (listener in coordinateCreatedListeners) {
            listener.coordinateWasCreated(coordinate)
        }
    }

}
