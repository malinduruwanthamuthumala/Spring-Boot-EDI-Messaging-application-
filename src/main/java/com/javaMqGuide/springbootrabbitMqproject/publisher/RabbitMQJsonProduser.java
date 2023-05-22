package com.javaMqGuide.springbootrabbitMqproject.publisher;


import com.javaMqGuide.springbootrabbitMqproject.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProduser {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.json.key}")
    private String routingJson;

    private org.springframework.amqp.rabbit.core.RabbitTemplate RabbitTemplate;

    RabbitMQJsonProduser(RabbitTemplate template){
        RabbitTemplate = template;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonProduser.class);
    public void sendMessage(User user){
        LOGGER.info(String.format("message sent -> %s",user.toString()));
        RabbitTemplate.convertAndSend(exchange,routingJson,user);
    }



}
