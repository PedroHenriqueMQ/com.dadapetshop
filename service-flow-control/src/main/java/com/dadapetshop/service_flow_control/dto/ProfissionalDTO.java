package com.dadapetshop.service_flow_control.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record ProfissionalDTO(
    @NotBlank(message = "Campo nome não pode estar vazio.")
    String nome,
    @NotBlank(message = "Campo CPF não pode estar vazio.")
    @CPF(message = "CPF fornecido não está no formato de CPF.")
    String cpf,
    @NotBlank(message = "Campo cargo não pode estar vazio.")
    String cargo
) {}
