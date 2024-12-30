package com.aymanba.kafkagettingstarted.service.consumer;

import com.aymanba.kafkagettingstarted.model.PhoneEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(
        prefix = "kafka-config",
        name = "transfer-mode",
        havingValue = "WITH_DEFAULT_MODE"
)
public class DefaultPhoneConsumerService implements PhoneConsumerService<PhoneEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultPhoneConsumerService.class);

    @Override
    @KafkaListener(
            groupId = "${kafka-config.consumer.group-id}",
            topics = "${kafka-config.topic-name.phones}"
    )
    public void receive(@Header(KafkaHeaders.RECEIVED_KEY) String key,
                        @Payload PhoneEvent phone,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                        @Header(KafkaHeaders.OFFSET) Long offset) {
        LOGGER.info("PhoneEvent : {} received with key {}, partition {} and offset {}, ",
                phone, key, partition, offset);
    }
}
