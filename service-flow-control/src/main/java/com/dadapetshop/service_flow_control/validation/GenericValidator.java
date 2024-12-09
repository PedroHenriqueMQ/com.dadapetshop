package com.dadapetshop.service_flow_control.validation;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GenericValidator<T> {
    private final Validator validator;

    public boolean validarObjeto(T object) {
        var violations = validator.validate(object);

        if (!violations.isEmpty()) {
            StringBuilder errorMsg = new StringBuilder();

            violations.forEach(v -> errorMsg.append(
                            String.format(
                                    "Validação falhou no campo %s com mensagem: %s",
                                    v.getPropertyPath(), v.getMessage()
                            )
                    )
            );

            throw new ConstraintViolationException(errorMsg.toString(), violations);
        }

        return true;
    }
}
