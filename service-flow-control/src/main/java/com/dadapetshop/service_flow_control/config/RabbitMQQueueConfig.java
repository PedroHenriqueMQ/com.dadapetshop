package com.dadapetshop.service_flow_control.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RabbitMQQueueConfig {
    private final RabbitAdmin rabbitAdmin;
    private final String petAttendanceQueue;
    private final String productPurchaseQueue;
    private final String exchange;

    public void createPetAttendanceQueue() {
        rabbitAdmin.declareQueue(QueueBuilder
                .durable(petAttendanceQueue)
                .build());
    }

    public void createProductPurchaseQueue() {
        rabbitAdmin.declareQueue(QueueBuilder
                .durable(productPurchaseQueue)
                .build());
    }

    public void createExchange() {
        rabbitAdmin.declareExchange(ExchangeBuilder
                .directExchange(exchange)
                .build()
        );  
    }

    public void createQueueBindingForPetAttendanceQueue() {
        rabbitAdmin.declareBinding(new Binding(
                petAttendanceQueue,
                Binding.DestinationType.QUEUE,
                exchange,
                petAttendanceQueue,
                null
            )
        );
    }

    public void createQueueBindingForProductPurchaseQueue() {
        rabbitAdmin.declareBinding(new Binding(
                productPurchaseQueue,
                Binding.DestinationType.QUEUE,
                exchange,
                productPurchaseQueue,
                null
            )
        );
    }
}
