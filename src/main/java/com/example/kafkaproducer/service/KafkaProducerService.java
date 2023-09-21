package com.example.kafkaproducer.service;

import com.example.kafkaproducer.models.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class KafkaProducerService {

    @Autowired
    KafkaTemplate<String,Product> kafkaTemplate;

    @Value("${topicName:test-topic}")
    private String topicName;

    public String sendMessage(Product product){
        try {
            CompletableFuture<SendResult<String, Product>> completableFuture = kafkaTemplate.send(topicName, product);
            SendResult sendResult = completableFuture.get();
            return sendResult.getRecordMetadata().toString();
        }catch (Exception exception){
            log.error("Exception while sending the message to kafka topic",exception);
            return "Exception while sending message to topic: "+topicName+" exception: "+exception.getMessage();
        }
    }
}
