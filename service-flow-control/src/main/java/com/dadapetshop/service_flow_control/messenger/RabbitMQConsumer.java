package com.dadapetshop.service_flow_control.messenger;

import com.dadapetshop.service_flow_control.constants.RabbitConstants;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {
    @RabbitListener(queues = RabbitConstants.PET_ATTENDANCE_QUEUE)
    public String receberValidacaoAtendimento(final Message message) {
        return new String(message.getBody());
    }
}
