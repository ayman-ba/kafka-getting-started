package com.aymanba.kafkagettingstarted.service.consumer;

import com.aymanba.kafkagettingstarted.model.PhoneEvent;
import com.aymanba.kafkagettingstarted.model.avro.PhoneAvroEvent;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
public class AvroPhoneConsumerService implements PhoneConsumerService<PhoneAvroEvent> {


    @Override
    public void receive(@Header(KafkaHeaders.RECEIVED_KEY) String key,
                        @Payload PhoneAvroEvent phone,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                        @Header(KafkaHeaders.OFFSET) Long offset) {

    }
}
