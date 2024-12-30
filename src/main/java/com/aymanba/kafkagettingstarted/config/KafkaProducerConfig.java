package com.aymanba.kafkagettingstarted.config;

import com.aymanba.kafkagettingstarted.config.properties.KafkaProperties;
import com.aymanba.kafkagettingstarted.model.PhoneEvent;
import com.aymanba.kafkagettingstarted.model.avro.PhoneAvroEvent;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;

@Configuration
@RequiredArgsConstructor
public class KafkaProducerConfig {

    private final KafkaProperties kafkaProperties;

    @Bean
    @ConditionalOnProperty(
            prefix = "kafka-config",
            name = "transfer-mode",
            havingValue = "WITH_DEFAULT_MODE"
    )
    public ProducerFactory<String, PhoneEvent> producerFactory() {
        var producerProperties = new HashMap<String, Object>();
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(producerProperties);
    }

    @Bean
    @ConditionalOnProperty(
            prefix = "kafka-config",
            name = "transfer-mode",
            havingValue = "WITH_DEFAULT_MODE"
    )
    public KafkaTemplate<String, PhoneEvent> kafkaTemplate(ProducerFactory<String, PhoneEvent> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }


    @Bean
    @ConditionalOnProperty(
            prefix = "kafka-config",
            name = "transfer-mode",
            havingValue = "WITH_AVRO_AND_SCHEMA_REGISTRY_MODE"
    )
    public ProducerFactory<String, PhoneAvroEvent> producerAvroFactory() {
        System.out.println("Avrooo");
        var producerAvroProperties = new HashMap<String, Object>();
        producerAvroProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        producerAvroProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerAvroProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        producerAvroProperties.put("schema.registry.url", kafkaProperties.getSchemaRegistryUrl());
        return new DefaultKafkaProducerFactory<>(producerAvroProperties);
    }

    @Bean
    @ConditionalOnProperty(
            prefix = "kafka-config",
            name = "transfer-mode",
            havingValue = "WITH_AVRO_AND_SCHEMA_REGISTRY_MODE"
    )
    public KafkaTemplate<String, PhoneAvroEvent> kafkaAvroTemplate(ProducerFactory<String, PhoneAvroEvent> producerAvroFactory) {
        return new KafkaTemplate<>(producerAvroFactory);
    }
}
