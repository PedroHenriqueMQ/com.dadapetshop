package com.dadapetshop.service_flow_control.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public record CompraDTO (
        @JsonProperty("codigo_fiscal")
        @NotBlank(message = "Campo código fiscal não pode estar vazio.")
        String codigoFiscal,
        @NotBlank(message = "Campo cliente não pode estar vazio.")
        String cliente,
        @NotEmpty(message = "Lista de produtos não pode estar vazia.")
        List<String> produtos,
        @JsonProperty("valor_total")
        @Min(message = "Valor total não pode ser menor que 0.", value = 0)
        @NotNull(message = "Campo valor total não pode ser nulo.")
        BigDecimal valorTotal
) { }