package com.aymanba.kafkagettingstarted.service;

import com.aymanba.kafkagettingstarted.config.properties.KafkaProperties;
import com.aymanba.kafkagettingstarted.config.properties.TopicNameProperties;
import com.aymanba.kafkagettingstarted.model.PhoneEvent;
import com.aymanba.kafkagettingstarted.request.PhoneRequest;
import com.aymanba.kafkagettingstarted.service.producer.DefaultPhoneProducerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PhoneProducerServiceTest {

    private static final String PHONES_TOPIC_NAME = "phones-topic-test";
    @Mock
    private KafkaTemplate<String, PhoneEvent> kafkaTemplateMock;

    @Mock
    private KafkaProperties kafkaPropertiesMock;

    private DefaultPhoneProducerService phoneProducerService;

    @BeforeEach
    void setup() {
        var topicNameProperties = new TopicNameProperties();
        topicNameProperties.setPhones(PHONES_TOPIC_NAME);
        when(kafkaPropertiesMock.getTopicName()).thenReturn(topicNameProperties);
        this.phoneProducerService = new DefaultPhoneProducerService(
                kafkaTemplateMock, kafkaPropertiesMock);
    }

    @Test
    void should_publish_phone_OK() {
        var phoneRequest = PhoneRequest.builder()
                .brand("HONOR")
                .model("HONOR 50")
                .price(280.0)
                .build();
        var result = phoneProducerService.publishPhone(phoneRequest);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("HONOR", result.getBrand());
        assertEquals("HONOR 50", result.getModel());
        assertEquals(280.0, result.getPrice());
        verify(kafkaTemplateMock).send(anyString(), anyString(), any());
    }
}
