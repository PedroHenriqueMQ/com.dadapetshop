package com.dadapetshop.registrations.messenger;


import com.dadapetshop.registrations.constants.RabbitConstants;
import com.dadapetshop.registrations.dto.CompraDTO;
import com.dadapetshop.registrations.exception.ConversaoDeMessageDTOFalhaException;
import com.dadapetshop.registrations.mapper.MessageGenericConversor;
import com.dadapetshop.registrations.security.AccountDetailsHolder;
import com.dadapetshop.registrations.service.ProdutoService;
import com.dadapetshop.registrations.service.UsuarioService;
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
    private final UsuarioService usuarioService;
    private final ProdutoService produtoService;
    private final AccountDetailsHolder accountDetailsHolder;

    @RabbitListener(queues = RabbitConstants.PRODUCT_PURCHASE_QUEUE)
    public boolean validarCompra(final Message message) {
        try {
            var compraDTO = conversor.deserializarMessageDTO(message.getBody(), CompraDTO.class);

            if (!accountDetailsHolder.isUserAuthenticated(compraDTO.cliente()))
                return false;

            if (usuarioService.findByEmail(compraDTO.cliente()).isEmpty())
                return false;

            for (String codigoProduto : compraDTO.produtos()) {
                if (produtoService.findProdutoByCodigo(codigoProduto) == null)
                    return false;
            }

            return true;
        } catch (ConversaoDeMessageDTOFalhaException fatalError) {
            log.error("Falha ao deserializar produtoDTO: {}", fatalError.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return false;
    }
}
