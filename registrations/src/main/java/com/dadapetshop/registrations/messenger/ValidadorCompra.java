package com.dadapetshop.registrations.messenger;


import com.dadapetshop.registrations.constants.RabbitConstants;
import com.dadapetshop.registrations.dto.CompraDTO;
import com.dadapetshop.registrations.dto.ProdutoDTO;
import com.dadapetshop.registrations.exception.ConversaoDeMessageDTOFalhaException;
import com.dadapetshop.registrations.mapper.MessageGenericConversor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class ValidadorCompra {
    private final MessageGenericConversor conversor;

    @RabbitListener(queues = RabbitConstants.PRODUCT_PURCHASE_QUEUE)
    public void validarCompra(final Message message) {
        try {
            var produto = conversor.deserializarMessageDTO(message.getBody(), CompraDTO.class);
            System.out.println(produto);
        } catch (ConversaoDeMessageDTOFalhaException fatalError) {
            log.error("Falha ao deserializar produtoDTO: {}", fatalError.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
