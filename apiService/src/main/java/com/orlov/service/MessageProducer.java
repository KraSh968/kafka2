package com.orlov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Value(value = "${kafka.topic.name}")
    private String topicEmployeeName;
    private String topicDepartmentName = "topic2";


    public void sendMessage(String data) {
        kafkaTemplate.send(topicEmployeeName, data);
    }
    public void sendSubject(String data) {
        kafkaTemplate.send(topicDepartmentName, data);
    }
}
