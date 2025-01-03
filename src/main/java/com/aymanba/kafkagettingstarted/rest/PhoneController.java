package com.aymanba.kafkagettingstarted.rest;

import com.aymanba.kafkagettingstarted.model.PhoneEvent;
import com.aymanba.kafkagettingstarted.request.PhoneRequest;
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
        havingValue = "WITH_DEFAULT_MODE"
)
public class PhoneController {

    private final DefaultPhoneProducerService defaultPhoneProducerService;

    @PostMapping("/publish")
    public ResponseEntity<PhoneEvent> publishPhone(@RequestBody PhoneRequest phoneRequest) {
        return new ResponseEntity<>(
                defaultPhoneProducerService.publishPhone(phoneRequest),
                HttpStatus.CREATED);
    }
}
