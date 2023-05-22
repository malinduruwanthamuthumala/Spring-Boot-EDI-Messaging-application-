package com.javaMqGuide.springbootrabbitMqproject.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RabbitMQConcumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConcumer.class);

    public static ArrayList<String> messageList;

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consume(String message){
        if (messageList == null){
            messageList = new ArrayList<String>();
        }
        messageList.add(message);
        LOGGER.info(String.format("Received mesage -> %s",message));
    }

    public static ArrayList<String> printMessages(){
       return messageList;
    }



}
