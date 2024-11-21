package com.dadapetshop.registrations.dto;

import java.math.BigDecimal;

import com.dadapetshop.registrations.model.ProdutoCategoria;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;

public record ProdutoDTO(
        String codigo,
        String nome,
        @JsonProperty("quantidade")
        @Min(message = "Quantidade não pode ser menor que 0.", value = 0)
        int quantidadeEstoque,
        @Min(message = "Valor não pode ser menor que 0.", value = 0)
        BigDecimal valor,
        ProdutoCategoria categoria
) {}
