package com.dadapetshop.registrations.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record ProfissionalDTO(
    @NotBlank(message = "Campo nome não pode estar vazio.")
    String nome,
    @CPF(message = "CPF fornecido não está no formato de CPF.")
    String cpf,
    @NotBlank(message = "Campo cargo não pode estar vazio.")
    String cargo
) {}
