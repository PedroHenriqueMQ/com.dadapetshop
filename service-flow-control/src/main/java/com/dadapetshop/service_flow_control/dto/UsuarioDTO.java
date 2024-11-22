package com.dadapetshop.service_flow_control.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(
    @Email(message = "Email fornecido não está no formato de email.")
    String email,
    @NotBlank(message = "Campo senha não pode estar vazio.")
    String senha,
    @NotBlank(message = "Campo nome não pode estar vazio.")
    String nome,
    List<PetDTO> pets
) {}
