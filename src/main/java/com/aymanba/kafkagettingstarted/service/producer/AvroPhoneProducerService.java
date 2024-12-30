package com.aymanba.kafkagettingstarted.service.producer;

import com.aymanba.kafkagettingstarted.config.properties.KafkaProperties;
import com.aymanba.kafkagettingstarted.model.avro.PhoneAvroEvent;
import com.aymanba.kafkagettingstarted.request.PhoneRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor(
        access = AccessLevel.PACKAGE
)
@ConditionalOnProperty(
        prefix = "kafka-config",
        name = "transfer-mode",
        havingValue = "WITH_AVRO_AND_SCHEMA_REGISTRY_MODE"
)
public class AvroPhoneProducerService implements PhoneProducerService<PhoneAvroEvent> {

    private final KafkaTemplate<String, PhoneAvroEvent> kafkaAvroTemplate;
    private final KafkaProperties kafkaProperties;

    @Override
    public PhoneAvroEvent publishPhone(PhoneRequest phoneRequest) {
        var generatedId = UUID.randomUUID();
        var phoneAvroEvent = PhoneAvroEvent.newBuilder()
                .setId(generatedId)
                .setBrand(phoneRequest.getBrand())
                .setModel(phoneRequest.getModel())
                .setPrice(phoneRequest.getPrice())
                .build();
        kafkaAvroTemplate.send(kafkaProperties.getTopicName().getPhones(),
                generatedId.toString(), phoneAvroEvent);
        return phoneAvroEvent;
    }
}
