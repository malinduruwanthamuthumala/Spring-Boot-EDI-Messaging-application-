package com.javaMqGuide.springbootrabbitMqproject.controller;

import com.javaMqGuide.springbootrabbitMqproject.publisher.RabbitMQProduser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class messageController {

    @Autowired
    private RabbitMQProduser produser;

    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message){
        produser.sendMessage(message);
        return ResponseEntity.ok("Message send successfullt to RabbitMQ");

    }

}
