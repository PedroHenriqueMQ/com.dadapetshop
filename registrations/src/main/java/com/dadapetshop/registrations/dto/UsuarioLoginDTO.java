package com.dadapetshop.registrations.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioLoginDTO(
        @Email(message = "Email informado não é válido.")
        String email,
        @NotBlank(message = "Este campo não pode estar vazio.")
        String senha
) { }
