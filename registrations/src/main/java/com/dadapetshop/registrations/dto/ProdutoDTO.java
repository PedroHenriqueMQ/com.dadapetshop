package com.dadapetshop.registrations.dto;

import java.math.BigDecimal;

import com.dadapetshop.registrations.model.ProdutoCategoria;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoDTO(
        @NotBlank(message = "Campo produto não pode estar vazio.")
        String codigo,
        @NotBlank(message = "Campo nome não pode estar vazio.")
        String nome,
        @NotNull(message = "Campo quantidade não pode estar nulo.")
        @JsonProperty("quantidade")
        @Min(message = "Quantidade não pode ser menor que 0.", value = 0)
        int quantidadeEstoque,
        @NotNull(message = "Campo valor não pode estar nulo.")
        @Min(message = "Valor não pode ser menor que 0.", value = 0)
        BigDecimal valor,
        @NotNull(message = "Campo categoria não pode estar nulo.")
        ProdutoCategoria categoria,
        @JsonProperty("caminho_imagem")
        String caminhoImagem
) {}
