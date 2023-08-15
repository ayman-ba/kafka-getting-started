package com.aymanba.kafkagettingstarted.rest;

import com.aymanba.kafkagettingstarted.model.PhoneEvent;
import com.aymanba.kafkagettingstarted.request.PhoneRequest;
import com.aymanba.kafkagettingstarted.service.PhoneProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/phones")
@RequiredArgsConstructor
public class PhoneController {

    private final PhoneProducerService phoneProducerService;

    @PostMapping("/publish")
    public ResponseEntity<PhoneEvent> publishPhone(@RequestBody PhoneRequest phoneRequest) {
        return new ResponseEntity<>(
                phoneProducerService.publishPhone(phoneRequest),
                HttpStatus.CREATED);
    }
}
