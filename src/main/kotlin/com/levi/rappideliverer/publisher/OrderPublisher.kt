package com.levi.rappideliverer.publisher

import com.levi.rappideliverer.domain.Order
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component
import java.text.ParseException

@Component
class RatingPublisher(private val kafkaTemplate: KafkaTemplate<String, Order>) {

    @Value("\${spring.kafka.topic.order}")
    var topicRating: String? = null

    @Throws(ParseException::class)
    fun sendRatingToTopic(order: Order) {
        val message = MessageBuilder
                .withPayload(order)
                .setHeader(KafkaHeaders.TOPIC, topicRating)
                .build()
        kafkaTemplate.send(message)
    }

}
