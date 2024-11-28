package com.dadapetshop.service_flow_control.service;

import com.dadapetshop.service_flow_control.dto.ConsultaDTO;
import com.dadapetshop.service_flow_control.dto.ProcedimentoDTO;
import com.dadapetshop.service_flow_control.model.Procedimento;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dadapetshop.service_flow_control.constants.RabbitConstants;
import com.dadapetshop.service_flow_control.messenger.RabbbitMQProducer;

@Service
public class AtendimentoService extends RabbbitMQProducer {
    @Autowired
    private

    public AtendimentoService(RabbitTemplate rabbitTemplate) {
        super(rabbitTemplate);
    }

    public Boolean validarAtendimento(Object atendimentoDTO) {
        return sendMessageAndReceive(RabbitConstants.PET_ATTENDANCE_QUEUE, atendimentoDTO);
    }

    public void marcarProcedimento(ProcedimentoDTO procedimentoDTO) {

    }

    public void marcarConsulta(ConsultaDTO consultaDTO) {

    }
}
