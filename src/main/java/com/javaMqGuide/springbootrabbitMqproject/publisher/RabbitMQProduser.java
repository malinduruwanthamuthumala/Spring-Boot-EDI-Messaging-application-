package com.javaMqGuide.springbootrabbitMqproject.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProduser {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingkey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProduser.class);
    private RabbitTemplate rabbitMqTemplate;

    @Autowired
    public RabbitMQProduser(RabbitTemplate rabbitMqTemplate) {
        this.rabbitMqTemplate = rabbitMqTemplate;
    }

    public void sendMessage(String message){
        LOGGER.info(String.format("message sent -> %s",message));
        rabbitMqTemplate.convertAndSend(exchange,routingkey,message);
    }
}
