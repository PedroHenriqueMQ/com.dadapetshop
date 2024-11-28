package com.dadapetshop.registrations.messenger;

import java.io.IOException;

import com.dadapetshop.registrations.dto.ConsultaDTO;
import com.dadapetshop.registrations.dto.ProcedimentoDTO;
import com.dadapetshop.registrations.exception.ConversaoDeMessageDTOFalhaException;
import com.dadapetshop.registrations.mapper.MessageGenericConversor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.dadapetshop.registrations.constants.RabbitConstants;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Slf4j
public class ValidadorAtendimento {
    private final MessageGenericConversor conversor;

    @RabbitListener(queues = RabbitConstants.PET_ATTENDANCE_QUEUE)
    public void validarAtendimento(final Message message) {
        try {
            var procedimento = conversor.deserializarMessageDTO(message.getBody(), ProcedimentoDTO.class);
            System.out.println(procedimento);
            return;
        } catch (ConversaoDeMessageDTOFalhaException e) {
            log.error("Falha ao deserializar ProcedimentoDTO: {}", e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        try {
            var consulta = conversor.deserializarMessageDTO(message.getBody(), ConsultaDTO.class);
            System.out.println(consulta);
        } catch (ConversaoDeMessageDTOFalhaException fatalError) {
            log.error("Falha ao deserializar ConsultaDTO: {}", fatalError.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}