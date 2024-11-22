package com.dadapetshop.service_flow_control.validation.annotation;

import com.dadapetshop.service_flow_control.validation.HorarioPermitidoValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = HorarioPermitidoValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface HorarioPermitido {
    String message() default "Horário inválido: deve estar entre 08:00 e 17:00, excluindo o intervalo de almoço (12:00 às 13:00).";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
