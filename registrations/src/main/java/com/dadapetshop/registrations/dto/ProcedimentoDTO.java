package com.dadapetshop.registrations.dto;

import com.dadapetshop.registrations.validation.annotation.HorarioPermitido;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProcedimentoDTO (
    @JsonProperty("codigo_fiscal")
    @NotBlank(message = "Campo código fiscal não pode estar vazio.")
    String codigoFiscal,
    @NotBlank(message = "Campo atendente não pode estar vazio.")
    String atendente,
    @NotBlank(message = "Campo cliente não pode estar vazio.")
    String cliente,
    @NotBlank(message = "Campo procedimento não pode estar vazio.")
    String procedimento,
    @JsonProperty("horario_marcado")
    @HorarioPermitido
    LocalDateTime horarioMarcado,
    @NotNull(message = "Campo valor não pode estar nulo.")
    BigDecimal valor
) { }
