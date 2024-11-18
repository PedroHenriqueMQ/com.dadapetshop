package com.dadapetshop.registrations.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.dadapetshop.registrations.dto.UsuarioDTO;
import com.dadapetshop.registrations.model.Usuario;

@Mapper(componentModel = "spring", uses = {PetMapperHelper.class})
public interface UsuarioMapper {
    @Mapping(target = "pets", source = "pets", qualifiedByName = "petListToPetDTOList")
    UsuarioDTO toDTO(Usuario usuario);
    @Mapping(target = "pets", source = "pets", qualifiedByName = "petDTOListToPetList")
    Usuario toEntity(UsuarioDTO usuarioDTO);
}