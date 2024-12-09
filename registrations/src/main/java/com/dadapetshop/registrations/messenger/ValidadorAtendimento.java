package com.dadapetshop.registrations.messenger;

import java.io.IOException;
import java.util.Objects;

import com.dadapetshop.registrations.dto.ConsultaDTO;
import com.dadapetshop.registrations.dto.ProcedimentoDTO;
import com.dadapetshop.registrations.dto.ProdutoDTO;
import com.dadapetshop.registrations.exception.ConversaoDeMessageDTOFalhaException;
import com.dadapetshop.registrations.mapper.MessageGenericConversor;
import com.dadapetshop.registrations.service.ProdutoService;
import com.dadapetshop.registrations.service.ProfissionalService;
import com.dadapetshop.registrations.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import com.dadapetshop.registrations.constants.RabbitConstants;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Slf4j
public class ValidadorAtendimento {
    private final MessageGenericConversor conversor;
    private final RabbitTemplate rabbitTemplate;

    private final ProdutoService produtoService;
    private final UsuarioService usuarioService;
    private final ProfissionalService profissionalService;

    @RabbitListener(queues = RabbitConstants.PET_ATTENDANCE_QUEUE)
    public boolean validarAtendimento(final Message message) {
        try {
            var procedimentoDTO = conversor.deserializarMessageDTO(message.getBody(), ProcedimentoDTO.class);

            if (usuarioValido(procedimentoDTO.cliente()) && profissionalValido(procedimentoDTO.atendente()))
                return true;

            return false;
        } catch (ConversaoDeMessageDTOFalhaException | IOException e) {
            log.error("Falha ao deserializar ProcedimentoDTO: {}", e.getMessage());
        }

        try {
            var consultaDTO = conversor.deserializarMessageDTO(message.getBody(), ConsultaDTO.class);

            if (usuarioValido(consultaDTO.cliente()) && profissionalValido(consultaDTO.veterinario())) {
                for (ProdutoDTO produtoDTO : consultaDTO.medicamentos())
                    if (!produtoValido(produtoDTO)) return false;

                return true;
            }

            return false;
        } catch (ConversaoDeMessageDTOFalhaException | IOException e) {
            log.error("Falha ao deserializar ConsultaDTO: {}", e.getMessage());
        }

        return false;
    }

    private boolean usuarioValido(String usuarioEmail) {
        return usuarioService.findByEmail(usuarioEmail).isPresent();
    }

    private boolean profissionalValido(String profissionalCPF) {
        return profissionalService.findByCpf(profissionalCPF).isPresent();
    }

    private boolean produtoValido(ProdutoDTO produtoDTO) {
        var produtoEncontrado = produtoService.findProdutoByCodigo(produtoDTO.codigo());

        return Objects.equals(produtoEncontrado, produtoDTO);
    }
}