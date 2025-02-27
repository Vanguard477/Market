package com.website.market.kafkaComponent;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {
    private static final String TOPIC = "test-topic";
    private final KafkaTemplate<String, String> kafkaTemplate;


    public void sendMessageToTopic(Object data) {
        log.info("sending object='{}' to topic='{}'", data, TOPIC);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            kafkaTemplate.send(TOPIC, objectMapper.writeValueAsString(data));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
