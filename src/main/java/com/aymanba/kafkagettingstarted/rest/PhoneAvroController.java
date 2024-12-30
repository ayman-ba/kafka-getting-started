package com.aymanba.kafkagettingstarted.rest;

import com.aymanba.kafkagettingstarted.model.PhoneEvent;
import com.aymanba.kafkagettingstarted.model.avro.PhoneAvroEvent;
import com.aymanba.kafkagettingstarted.request.PhoneRequest;
import com.aymanba.kafkagettingstarted.service.producer.AvroPhoneProducerService;
import com.aymanba.kafkagettingstarted.service.producer.DefaultPhoneProducerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/phones")
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@ConditionalOnProperty(
        prefix = "kafka-config",
        name = "transfer-mode",
        havingValue = "WITH_AVRO_AND_SCHEMA_REGISTRY_MODE"
)
public class PhoneAvroController {

    private final AvroPhoneProducerService avroPhoneProducerService;

    @PostMapping("/publish")
    public ResponseEntity<PhoneAvroEvent> publishPhone(@RequestBody PhoneRequest phoneRequest) {
        return new ResponseEntity<>(
                avroPhoneProducerService.publishPhone(phoneRequest),
                HttpStatus.CREATED);
    }
}
