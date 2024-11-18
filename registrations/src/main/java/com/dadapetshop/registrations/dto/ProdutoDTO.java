package com.dadapetshop.registrations.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProdutoDTO(
    String codigo,
    String nome,
    @JsonProperty("quantidade")
    String quantidadeEstoque,
    BigDecimal valor
) {}
