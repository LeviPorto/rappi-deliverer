package com.levi.rappideliverer.listener

import com.levi.rappideliverer.dto.CoordinateDTO

interface CoordinateCreatedListener {

    fun coordinateWasCreated(coordinateDTO: CoordinateDTO)

}
