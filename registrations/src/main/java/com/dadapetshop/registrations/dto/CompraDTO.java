package com.dadapetshop.registrations.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CompraDTO {
    @JsonProperty("codigo_fiscal")
    @NotBlank(message = "Campo código fiscal não pode estar vazio.")
    private String codigoFiscal;
    @NotBlank(message = "Campo atendente não pode estar vazio.")
    private String atendente;
    @NotBlank(message = "Campo cliente não pode estar vazio.")
    private String cliente;
    @NotEmpty(message = "Lista de produtos não pode estar vazia.")
    private List<ProdutoDTO> produtos;
    @JsonProperty("valor_total")
    @Min(message = "Valor total não pode ser menor que 0.", value = 0)
    @NotNull(message = "Campo valor total não pode ser nulo.")
    private BigDecimal valorTotal;
}