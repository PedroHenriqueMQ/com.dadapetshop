package com.dadapetshop.registrations.dto;

import java.util.List;

import jakarta.validation.constraints.Email;

public record UsuarioDTO(
    @Email(message = "Email fornecido não está no formato de email.")
    String email,
    String senha,
    String nome,
    List<PetDTO> pets
) {}
