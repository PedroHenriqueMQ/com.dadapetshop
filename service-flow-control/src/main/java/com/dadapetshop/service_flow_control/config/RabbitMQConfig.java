package com.dadapetshop.service_flow_control.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.dadapetshop.service_flow_control.constants.RabbitConstants;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@Configuration
@ConfigurationProperties(prefix = "spring.rabbit")
public class RabbitMQConfig {
    private String host = "localhost";
    private int port = 5672;
    private String username = "guest";
    private String password = "guest";
    
    @Bean
    public ConnectionFactory connectionFactory() {
        var connectioFactory = new CachingConnectionFactory();
        connectioFactory.setAddresses(host);
        connectioFactory.setUsername(username);
        connectioFactory.setPassword(password);
        connectioFactory.setPort(port);
        return connectioFactory;
    }

    @Bean
    @Primary
    public RabbitTemplate rabbitTemplate(final Jackson2JsonMessageConverter converter) {
        var rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMessageConverter(converter);
        rabbitTemplate.setReplyTimeout(10000);
        return rabbitTemplate;
    }

    @Bean
    @Primary
    public RabbitAdmin rabbitAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean(RabbitConstants.PET_ATTENDANCE_ROUTING_KEY)
    public SimpleRabbitListenerContainerFactory petAttendanceQueueListenerFactory() {
        var simpleListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        simpleListenerContainerFactory.setConnectionFactory(connectionFactory());
        return simpleListenerContainerFactory;
    }

    @Bean(RabbitConstants.PRODUCT_PURCHASE_ROUTING_KEY)
    public SimpleRabbitListenerContainerFactory productPurchaseQueueListenerFactory() {
        var simpleListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        simpleListenerContainerFactory.setConnectionFactory(connectionFactory());
        return simpleListenerContainerFactory;
    }
}

