package com.dadapetshop.service_flow_control.service;

import com.dadapetshop.service_flow_control.constants.RabbitConstants;
import com.dadapetshop.service_flow_control.dto.CompraDTO;
import com.dadapetshop.service_flow_control.messenger.RabbbitMQProducer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@Service
public class CompraService extends RabbbitMQProducer {

    public CompraService(RabbitTemplate rabbitTemplate) {
        super(rabbitTemplate);
    }

    public void validarCompra(CompraDTO compraDTO) {
        sendMessage(RabbitConstants.PRODUCT_PURCHASE_QUEUE, compraDTO);
    }
}
