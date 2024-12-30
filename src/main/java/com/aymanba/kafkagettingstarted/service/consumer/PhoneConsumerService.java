package com.aymanba.kafkagettingstarted.service.consumer;

public interface PhoneConsumerService<T> {

    void receive(String key, T phone, Integer partition, Long offset);
}
