package com.dadapetshop.registrations.dto;

import java.util.List;

public record UsuarioDTO(
    String email,
    String senha,
    String nome,
    List<PetDTO> pets
) {}
