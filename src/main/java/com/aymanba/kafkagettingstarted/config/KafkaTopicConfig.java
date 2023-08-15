package com.aymanba.kafkagettingstarted.config;

import com.aymanba.kafkagettingstarted.config.properties.KafkaProperties;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@RequiredArgsConstructor
public class KafkaTopicConfig {

    private final KafkaProperties kafkaProperties;

    @Bean
    public NewTopic phonesTopic() {
        return TopicBuilder.name(
                kafkaProperties.getTopicName().getPhones()
        ).build();
    }
}
