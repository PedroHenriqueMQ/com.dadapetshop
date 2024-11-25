package com.dadapetshop.registrations.validation;

import com.dadapetshop.registrations.validation.annotation.HorarioPermitido;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class HorarioPermitidoValidator implements ConstraintValidator<HorarioPermitido, LocalDateTime> {

    @Override
    public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
        if (value == null)
            return false;

        LocalTime horario = value.toLocalTime();

        LocalTime inicioExpediente = LocalTime.of(8, 0);
        LocalTime fimExpediente = LocalTime.of(17, 0);

        LocalTime inicioAlmoco = LocalTime.of(12, 0);
        LocalTime fimAlmoco = LocalTime.of(13, 0);

        if (horario.isBefore(inicioExpediente) || horario.isAfter(fimExpediente))
            return false;

        if (!horario.isBefore(inicioAlmoco) && !horario.isAfter(fimAlmoco))
            return false;

        return true;
    }
}
