package com.aymanba.kafkagettingstarted.service;

import com.aymanba.kafkagettingstarted.model.PhoneEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
@Service
public class PhoneConsumerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PhoneConsumerService.class);

    @KafkaListener(
            groupId = "${kafka-config.consumer.group-id}",
            topics = "${kafka-config.topic-name.phones}"
    )
    void receive(@Header(KafkaHeaders.RECEIVED_KEY) String key,
                 @Payload PhoneEvent phone,
                 @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                 @Header(KafkaHeaders.OFFSET) Long offset) {
        LOGGER.info("Phone : {} received with key {}, partition {} and offset {}, ",
                phone, key, partition, offset);
    }
}
