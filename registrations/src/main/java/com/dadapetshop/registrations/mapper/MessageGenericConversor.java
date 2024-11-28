package com.dadapetshop.registrations.mapper;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.dadapetshop.registrations.exception.ConversaoDeMessageDTOFalhaException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageGenericConversor {
    private final ObjectMapper objectMapper;
    private final Validator validator;
    
    public <T> T deserializarMessageDTO(byte[] messageBody, Class<T> classType) throws IOException {
        var messageDTO = objectMapper.readValue(messageBody, classType);
        var violations = validator.validate(messageDTO);

        if (!violations.isEmpty()) {
            StringBuilder errorMsg = new StringBuilder();

            violations.forEach(v -> errorMsg.append(
                        String.format(
                            "Validação falhou no campo %s com mensagem: %s",
                            v.getPropertyPath(), v.getMessage()
                    )
                )
            );

            throw new ConversaoDeMessageDTOFalhaException(errorMsg.toString());
        }

        return messageDTO;
    }
}
