package com.dadapetshop.registrations.mapper;

import org.mapstruct.Mapper;

import com.dadapetshop.registrations.dto.UsuarioDTO;
import com.dadapetshop.registrations.model.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioDTO toDTO(Usuario usuario);
    Usuario toEntity(UsuarioDTO usuarioDTO);
}