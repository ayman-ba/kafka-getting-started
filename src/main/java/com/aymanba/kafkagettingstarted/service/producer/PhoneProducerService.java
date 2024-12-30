package com.aymanba.kafkagettingstarted.service.producer;

import com.aymanba.kafkagettingstarted.request.PhoneRequest;

public interface PhoneProducerService<T> {

    T publishPhone(PhoneRequest phoneRequest);
}
