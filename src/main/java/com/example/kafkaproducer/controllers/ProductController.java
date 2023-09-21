package com.example.kafkaproducer.controllers;

import com.example.kafkaproducer.models.Product;
import com.example.kafkaproducer.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/product")
@RestController
public class ProductController {

    @Autowired
    KafkaProducerService kafkaProducerService;

    @PostMapping
    public String createProduct(@RequestBody Product product){
        return kafkaProducerService.sendMessage(product);
    }
}
