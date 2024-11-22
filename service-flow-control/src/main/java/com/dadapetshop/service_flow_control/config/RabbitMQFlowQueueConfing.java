package com.dadapetshop.service_flow_control.config;

import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Configuration;

import com.dadapetshop.service_flow_control.constants.RabbitConstants;

import jakarta.annotation.PostConstruct;

@Configuration
public class RabbitMQFlowQueueConfing extends RabbitMQQueueConfig {
    public RabbitMQFlowQueueConfing(RabbitAdmin rabbitAdmin) {
        super(
            rabbitAdmin, 
            RabbitConstants.PET_ATTENDANCE_QUEUE, 
            RabbitConstants.PRODUCT_PURCHASE_QUEUE, 
            RabbitConstants.EXCHANGE_NAME
        );
    }
    
    @PostConstruct
    public void createRabbitComponents() {
        createPetAttendanceQueue();
        createProductPurchaseQueue();

        createExchange();

        createQueueBindingForPetAttendanceQueue();
        createQueueBindingForProductPurchaseQueue();
    }
}
