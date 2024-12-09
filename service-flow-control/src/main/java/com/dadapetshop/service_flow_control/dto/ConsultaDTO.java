package com.dadapetshop.service_flow_control.dto;

import com.dadapetshop.service_flow_control.validation.annotation.HorarioPermitido;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


public record ConsultaDTO (
    @NotBlank(message = "Campo código fiscal não pode estar vazio.")
    @JsonProperty("codigo_fiscal")
    String codigoFiscal,
    @NotBlank(message = "Campo veterinário não pode estar vazio.")
    String veterinario,
    @NotBlank(message = "Campo cliente não pode estar vazio.")
    String cliente,
    @HorarioPermitido
    @NotNull(message = "Campo horário não pode ser nulo.")
    @JsonProperty("horario_marcado")
    LocalDateTime horarioMarcado,
    @NotBlank(message = "Campo status não pode estar vazio.")
    String status,
    List<ProdutoDTO> medicamentos,
    @Min(message = "O valor não pode ser menor que 0.", value = 0)
    @NotNull(message = "Campo valor não pode ser nulo.")
    BigDecimal valor
) implements AtendimentoDTO { }
