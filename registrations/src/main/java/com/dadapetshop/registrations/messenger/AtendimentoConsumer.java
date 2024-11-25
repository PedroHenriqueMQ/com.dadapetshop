package com.dadapetshop.registrations.messenger;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.dadapetshop.registrations.constants.RabbitConstants;
import com.dadapetshop.registrations.dto.CompraDTO;
import com.dadapetshop.registrations.dto.ConsultaDTO;
import com.dadapetshop.registrations.dto.ProcedimentoDTO;
import com.dadapetshop.registrations.exception.ConversaoDeAtendimentoFalhaException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AtendimentoConsumer {
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = RabbitConstants.PET_ATTENDANCE_QUEUE)
    public void validarAtendimento(final Message message) throws IOException {   
        var procedimento = deserializarProcedimentoDTO(message.getBody());
        ConsultaDTO consulta = null;

        if (procedimento == null) 
            consulta = deserializarConsultaDTO(message.getBody());
        else
            return;
        
        if(consulta == null)
            throw new ConversaoDeAtendimentoFalhaException();
    }

    private ProcedimentoDTO deserializarProcedimentoDTO(byte[] messageBody) throws IOException {
        var procedimentoDTO = objectMapper.readValue(messageBody, ProcedimentoDTO.class);
        
        if (procedimentoDTO.getProcedimento() != null) return procedimentoDTO;
        return null;
    }

    private ConsultaDTO deserializarConsultaDTO(byte[] messageBody) throws IOException {
        var consultaDTO = objectMapper.readValue(messageBody, ConsultaDTO.class);
        
        if (consultaDTO.getStatus() != null) return consultaDTO;
        return null;
    }
}
