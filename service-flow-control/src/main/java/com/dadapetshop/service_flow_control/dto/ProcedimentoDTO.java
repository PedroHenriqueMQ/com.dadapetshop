package com.dadapetshop.service_flow_control.dto;

import com.dadapetshop.service_flow_control.validation.annotation.HorarioPermitido;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProcedimentoDTO (
    @NotBlank(message = "Campo código fiscal não pode estar vazio.")
    @JsonProperty("codigo_fiscal")
    String codigoFiscal,
    @NotBlank(message = "Campo atendente não pode estar vazio.")
    String atendente,
    @NotBlank(message = "Campo cliente não pode estar vazio.")
    String cliente,
    @NotBlank(message = "Campo procedimento não pode estar vazio.")
    String procedimento,
    @HorarioPermitido
    @JsonProperty("horario_marcado")
    LocalDateTime horarioMarcado,
    @NotNull(message = "Campo valor não pode estar nulo.")
    BigDecimal valor
) implements AtendimentoDTO { }
