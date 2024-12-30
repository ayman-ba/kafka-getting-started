package com.aymanba.kafkagettingstarted.service.producer;

import com.aymanba.kafkagettingstarted.request.PhoneRequest;
import com.aymanba.kafkagettingstarted.config.properties.KafkaProperties;
import com.aymanba.kafkagettingstarted.model.PhoneEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(
        prefix = "kafka-config",
        name = "transfer-mode",
        havingValue = "WITH_DEFAULT_MODE"
)
public class DefaultPhoneProducerService implements PhoneProducerService<PhoneEvent> {

    private final KafkaTemplate<String, PhoneEvent> kafkaTemplate;
    private final KafkaProperties kafkaProperties;

    @Override
    public PhoneEvent publishPhone(PhoneRequest phoneRequest) {
        var id = UUID.randomUUID();
        var phoneEvent = PhoneEvent.builder()
                .id(id)
                .brand(phoneRequest.getBrand())
                .model(phoneRequest.getModel())
                .price(phoneRequest.getPrice())
                .build();
        kafkaTemplate.send(kafkaProperties.getTopicName().getPhones(),
                id.toString(), phoneEvent);
        return phoneEvent;
    }
}
