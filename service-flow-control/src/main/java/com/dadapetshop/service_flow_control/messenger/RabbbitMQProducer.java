package com.dadapetshop.service_flow_control.messenger;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@RequiredArgsConstructor
public class RabbbitMQProducer {
    private final RabbitTemplate rabbitTemplate;

    protected void sendMessage(final String queue, final Object object) {
        rabbitTemplate.convertAndSend(queue, object);
    }
}
