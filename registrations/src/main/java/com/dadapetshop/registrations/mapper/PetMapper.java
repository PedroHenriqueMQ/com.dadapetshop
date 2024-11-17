package com.dadapetshop.registrations.mapper;

import org.mapstruct.Mapper;
import com.dadapetshop.registrations.dto.PetDTO;
import com.dadapetshop.registrations.model.Pet;

@Mapper(componentModel = "spring")
public interface PetMapper {
    PetDTO petToPetDTO(Pet pet);
    Pet petDTOToPet(PetDTO petDTO); 
}
