package com.javaMqGuide.springbootrabbitMqproject.controller;

import com.javaMqGuide.springbootrabbitMqproject.consumer.RabbitMQConcumer;
import com.javaMqGuide.springbootrabbitMqproject.dto.User;
import com.javaMqGuide.springbootrabbitMqproject.publisher.RabbitMQJsonProduser;
import com.javaMqGuide.springbootrabbitMqproject.publisher.RabbitMQProduser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MessageController {

    @Autowired
    private RabbitMQProduser produser;

    @Autowired
    private RabbitMQConcumer consumer;

    private RabbitMQJsonProduser jsoproduser;

    MessageController (RabbitMQJsonProduser jsonProduser) {
    this.jsoproduser = jsonProduser;
    }

    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message){
        produser.sendMessage(message);
        return ResponseEntity.ok("Message send successfullt to RabbitMQ");

    }

    @PostMapping("/publishJson")
    public ResponseEntity<String> sendMessageJson(@RequestBody User user){
        jsoproduser.sendMessage(user);
        return ResponseEntity.ok("Message send successfullt to RabbitMQ JOSN");

    }

    @GetMapping("/show")
    public ResponseEntity<String> getMessage(){
        consumer.printMessages();
        return ResponseEntity.ok("Message send successfullt to RabbitMQ" +  consumer.printMessages());
    }

}
