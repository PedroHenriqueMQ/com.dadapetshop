package com.dadapetshop.registrations.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import com.dadapetshop.registrations.dto.PetDTO;
import com.dadapetshop.registrations.model.Pet;
import com.dadapetshop.registrations.model.Usuario;

@Component
public class PetMapperHelper {
    @Named("petDTOToPet")
    public Pet petDTOToPet(PetDTO petDTO, Usuario tutor) {
        if (petDTO == null) return null;

        Pet pet = new Pet();
        pet.setNome(petDTO.nome());
        pet.setRaca(petDTO.raca());
        pet.setIdade(petDTO.idade());
        pet.setPeso(petDTO.peso());
        pet.setTutor(tutor); 
        return pet;
    }

    @Named("petToPetDTO")
    public PetDTO petToPetDTO(Pet pet) {
        if (pet == null) return null;

        return new PetDTO(
            pet.getNome(),
            pet.getRaca(),
            pet.getIdade(),
            pet.getPeso(),
            pet.getTutor().getEmail()
        );
    }

    @Named("petDTOListToPetList")
    public List<Pet> petDtoListToPetList(List<PetDTO> petDTOs, Usuario tutor) {
        if (petDTOs == null) return List.of();

        return petDTOs.stream()
                .map(petDTO -> petDTOToPet(petDTO, tutor))
                .collect(Collectors.toList());
    }

    @Named("petListToPetDTOList")
    public List<PetDTO> petListToPetDtoList(List<Pet> pets) {
        if (pets == null) return List.of();

        return pets.stream()
                .map(this::petToPetDTO)
                .collect(Collectors.toList());
    }
}
