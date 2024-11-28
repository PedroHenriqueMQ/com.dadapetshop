package com.dadapetshop.service_flow_control.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.dadapetshop.service_flow_control.constants.RabbitConstants;
import com.dadapetshop.service_flow_control.messenger.RabbbitMQProducer;

@Service
public class AtendimentoService extends RabbbitMQProducer {
    public AtendimentoService(RabbitTemplate rabbitTemplate) {
        super(rabbitTemplate);
    }

    public void validarAtendimento(Object atendimentoDTO) {
        sendMessage(RabbitConstants.PET_ATTENDANCE_QUEUE, atendimentoDTO);
    }
}
