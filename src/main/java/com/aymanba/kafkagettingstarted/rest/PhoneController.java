package com.aymanba.kafkagettingstarted.rest;

import com.aymanba.kafkagettingstarted.model.PhoneEvent;
import com.aymanba.kafkagettingstarted.request.PhoneRequest;
import com.aymanba.kafkagettingstarted.service.PhoneService;
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

    private final PhoneService phoneService;

    @PostMapping("/publish")
    public ResponseEntity<PhoneEvent> publishPhone(@RequestBody PhoneRequest phoneRequest){
        return new ResponseEntity<>(
                phoneService.publishPhone(phoneRequest),
                HttpStatus.CREATED);
    }
}
