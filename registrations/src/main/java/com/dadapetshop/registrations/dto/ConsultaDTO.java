package com.dadapetshop.registrations.dto;

import com.dadapetshop.registrations.validation.annotation.HorarioPermitido;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ConsultaDTO (
    @JsonProperty("codigo_fiscal")
    @NotBlank(message = "Campo código fiscal não pode estar vazio.")
    String codigoFiscal,
    @NotBlank(message = "Campo veterinário não pode estar vazio.")
    String veterinario,
    @NotBlank(message = "Campo cliente não pode estar vazio.")
    String cliente,
    @JsonProperty("horario_marcado")
    @HorarioPermitido
    @NotNull(message = "Campo horário não pode ser nulo.")
    LocalDateTime horarioMarcado,
    @NotBlank(message = "Campo status não pode estar vazio.")
    String status,
    List<ProdutoDTO> medicamentos,
    @Min(message = "O valor não pode ser menor que 0.", value = 0)
    @NotNull(message = "Campo valor não pode ser nulo.")
    BigDecimal valor
) { }
