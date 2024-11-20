package com.dadapetshop.registrations.dto;

import java.math.BigDecimal;

import com.dadapetshop.registrations.model.ProdutoCategoria;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ProdutoDTO(
        String codigo,
        String nome,
        @JsonProperty("quantidade")
        int quantidadeEstoque,
        BigDecimal valor,
        ProdutoCategoria categoria
) {}
