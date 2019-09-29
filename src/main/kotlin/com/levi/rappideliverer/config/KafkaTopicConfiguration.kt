package com.levi.rappideliverer.config

import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.KafkaAdmin
import java.util.HashMap

@Configuration
class KafkaTopicConfiguration {

    @Value("\${spring.kafka.bootstrap-servers}")
    var bootstrapServer: String? = null

    @Bean
    fun kafkaAdmin(): KafkaAdmin {
        val configs = HashMap<String, Any>()
        configs[AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServer!!
        return KafkaAdmin(configs)
    }

    @Bean
    fun topic(): NewTopic {
        return NewTopic("COORDINATE_EVENT_SOURCING", 1, 1.toShort())
    }
}
