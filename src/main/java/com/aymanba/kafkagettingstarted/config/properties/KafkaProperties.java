package com.aymanba.kafkagettingstarted.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("kafka-config")
public class KafkaProperties {

    private String bootstrapServers;
    private TopicNameProperties topicName;
}
