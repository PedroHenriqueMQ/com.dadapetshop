package com.dadapetshop.service_flow_control.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.dadapetshop.service_flow_control.constants.RabbitConstants;
import com.dadapetshop.service_flow_control.dto.ConsultaDTO;
import com.dadapetshop.service_flow_control.messenger.RabbbitMQProducer;

@Service
public class ConsultaService extends RabbbitMQProducer {
    public ConsultaService(RabbitTemplate rabbitTemplate) {
        super(rabbitTemplate);
    }

    public void validarConsulta(ConsultaDTO consultaDTO) {
        sendMessage(RabbitConstants.PET_ATTENDANCE_QUEUE, consultaDTO);
    }
}
