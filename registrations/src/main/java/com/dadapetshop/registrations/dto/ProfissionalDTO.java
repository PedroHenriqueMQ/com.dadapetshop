package com.dadapetshop.registrations.dto;

import org.hibernate.validator.constraints.br.CPF;

public record ProfissionalDTO(
    String nome,
    @CPF(message = "CPF fornecido não está no formato de CPF.")
    String cpf,
    String cargo
) {}
