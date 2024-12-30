package com.aymanba.kafkagettingstarted.config;

import com.aymanba.kafkagettingstarted.config.properties.KafkaProperties;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class KafkaTopicConfig {

    private final KafkaProperties kafkaProperties;

    @Bean
    @ConditionalOnProperty(
            prefix = "kafka-config",
            name = "transfer-mode",
            havingValue = "WITH_DEFAULT_MODE"
    )
    public NewTopic phonesTopic() {
        return TopicBuilder.name(
                kafkaProperties.getTopicName().getPhones()
        ).build();
    }

    @Bean
    @ConditionalOnProperty(
            prefix = "kafka-config",
            name = "transfer-mode",
            havingValue = "WITH_AVRO_AND_SCHEMA_REGISTRY_MODE"
    )
    public NewTopic phonesAvroTopic() {
        return TopicBuilder.name(
                kafkaProperties.getTopicName().getPhonesAvro()
        ).build();
    }
}
