package com.dadapetshop.service_flow_control.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.dadapetshop.service_flow_control.constants.RabbitConstants;
import com.dadapetshop.service_flow_control.dto.ProcedimentoDTO;
import com.dadapetshop.service_flow_control.messenger.RabbbitMQProducer;

@Service
public class ProcedimentoService extends RabbbitMQProducer {
    public ProcedimentoService(RabbitTemplate rabbitTemplate) {
        super(rabbitTemplate);
    }

    public void validarProcedimento(ProcedimentoDTO procedimentoDTO) {
        sendMessage(RabbitConstants.PET_ATTENDANCE_QUEUE, procedimentoDTO);
    }
}
