package com.levi.rappideliverer.config

import com.levi.rappideliverer.dto.CoordinateDTO
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer
import java.util.HashMap

@EnableKafka
@Configuration
class KafkaConsumerConfiguration {

    @Value("\${spring.kafka.bootstrap-servers}")
    val bootstrapServer: String? = null

    @Value("\${spring.kafka.topic.group}")
    val group: String? = null

    @Bean
    fun consumerFactory(): ConsumerFactory<String, CoordinateDTO> {
        val props = HashMap<String, Any>()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServer!!
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = JsonDeserializer::class.java
        props[ConsumerConfig.GROUP_ID_CONFIG] = group!!
        props[JsonDeserializer.VALUE_DEFAULT_TYPE] = CoordinateDTO::class.java
        props[JsonDeserializer.TRUSTED_PACKAGES] = "*"
        return DefaultKafkaConsumerFactory(props)
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, CoordinateDTO> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, CoordinateDTO>()
        factory.consumerFactory = consumerFactory()
        return factory
    }
}
