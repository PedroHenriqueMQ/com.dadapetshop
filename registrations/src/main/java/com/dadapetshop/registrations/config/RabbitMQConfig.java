package com.dadapetshop.registrations.config;

import com.dadapetshop.registrations.dto.CompraDTO;
import com.dadapetshop.registrations.dto.ConsultaDTO;
import com.dadapetshop.registrations.dto.ProcedimentoDTO;
import lombok.Setter;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "spring.rabbit")
@Setter
public class RabbitMQConfig {
    private String host;
    private int port;
    private String username;
    private String password;
    
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
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        rabbitTemplate.setReplyTimeout(5000);
        return rabbitTemplate;
    }

    @Bean
    @Primary
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public MessageConverter messageConverter() {
        var converter = new Jackson2JsonMessageConverter();
        var typeMapper = new DefaultJackson2JavaTypeMapper();

        Map<String, Class<?>> idClassMapping = new HashMap<>();
        idClassMapping.put("com.dadapetshop.service_flow_control.dto.ProcedimentoDTO", ProcedimentoDTO.class);
        idClassMapping.put("com.dadapetshop.service_flow_control.dto.ProcedimentoDTO", ConsultaDTO.class);
        idClassMapping.put("com.dadapetshop.service_flow_control.dto.CompraDTO", CompraDTO.class);

        typeMapper.setIdClassMapping(idClassMapping);
        typeMapper.setTrustedPackages("*");

        converter.setJavaTypeMapper(typeMapper);
        return converter;
    }
}
