package com.dadapetshop.registrations.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.dadapetshop.registrations.dto.PetDTO;
import com.dadapetshop.registrations.model.Pet;

@Mapper(componentModel = "spring", uses = {UsuarioMapperHelper.class})
public interface PetMapper {
    @Mapping(target = "emailTutor", source = "tutor", qualifiedByName = "usuarioToEmail")
    PetDTO toDTO(Pet pet);
    @Mapping(target = "tutor", source = "emailTutor", qualifiedByName = "emailToUsuario")
    Pet toEntity(PetDTO petDTO);
}
