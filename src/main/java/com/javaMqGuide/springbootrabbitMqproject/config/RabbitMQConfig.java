package com.javaMqGuide.springbootrabbitMqproject.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name}")
    private String queue;

    @Value("${rabbitmq.queue.forJson.name}")
    private String queueForJason;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.json.key}")
    private String routingjsonkey;

    @Value("${rabbitmq.routing.key}")
    private String routingkey;


    @Bean
    public Queue queue(){
        return new Queue(queue);
    }

   @Bean
   public Queue QueueJSON(){
        return new Queue(queueForJason);
   }
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    //binding between queue and exchange using routing key
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(routingkey);
    }


    @Bean
    public Binding bindingforJson(){
        return BindingBuilder.bind(QueueJSON())
                .to(exchange())
                .with(routingjsonkey);
    }


    @Bean
    public MessageConverter convertor(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory conectionFactory){
        RabbitTemplate template = new RabbitTemplate(conectionFactory);
        template.setMessageConverter(convertor());
        return template;
    }
}
