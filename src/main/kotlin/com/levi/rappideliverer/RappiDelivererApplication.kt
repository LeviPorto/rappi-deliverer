package com.levi.rappideliverer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableCaching
@EnableFeignClients
@EnableEurekaClient
class RappiDelivererApplication

fun main(args: Array<String>) {
    runApplication<RappiDelivererApplication>(*args)
}
