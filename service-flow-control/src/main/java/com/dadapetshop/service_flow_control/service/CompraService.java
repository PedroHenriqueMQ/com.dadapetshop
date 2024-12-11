package com.dadapetshop.service_flow_control.service;

import com.dadapetshop.service_flow_control.constants.RabbitConstants;
import com.dadapetshop.service_flow_control.dto.CompraDTO;
import com.dadapetshop.service_flow_control.messenger.RabbbitMQProducer;
import com.dadapetshop.service_flow_control.model.Compra;
import com.dadapetshop.service_flow_control.repository.CompraRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CompraService extends RabbbitMQProducer {
    private final CompraRepository compraRepository;

    @Autowired
    public CompraService(RabbitTemplate rabbitTemplate, CompraRepository compraRepository) {
        super(rabbitTemplate);
        this.compraRepository = compraRepository;
    }

    public boolean validarCompra(CompraDTO compraDTO) {
        return sendMessageAndReceiveValidityConfirmation(RabbitConstants.PRODUCT_PURCHASE_QUEUE, compraDTO);
    }

    public boolean realizarCompra(CompraDTO compraDTO) {
        if (compraRepository.findByCodigoFiscal(compraDTO.codigoFiscal()).isPresent())
            return false;

        var compra = Compra.builder()
                .usuarioComprou(compraDTO.cliente())
                .valorTotal(compraDTO.valorTotal())
                .produtos(compraDTO.produtos())
                .codigoFiscal(compraDTO.codigoFiscal())
                .build();

        compraRepository.save(compra);

        return true;
    }
}
