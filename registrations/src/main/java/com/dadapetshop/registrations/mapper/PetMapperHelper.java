package com.dadapetshop.registrations.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dadapetshop.registrations.dto.PetDTO;
import com.dadapetshop.registrations.model.Pet;
import com.dadapetshop.registrations.model.Usuario;
import com.dadapetshop.registrations.repository.UsuarioRepository;

@Component
public class PetMapperHelper {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Named("petDTOToPet")
    public Pet petDTOToPet(PetDTO petDTO, Usuario tutor) {
        if (petDTO == null)
            return null;

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
        if (pet == null)
            return null;

        return new PetDTO(
                pet.getNome(),
                pet.getRaca(),
                pet.getIdade(),
                pet.getPeso(),
                pet.getTutor().getEmail());
    }

    @Named("petDTOListToPetList")
    public List<Pet> petDTOListToPetList(List<PetDTO> petDTOs) {
        if (petDTOs == null)
            return Collections.emptyList();
        
        var tutor = usuarioRepository.findByEmail(petDTOs.getFirst().emailTutor());

        if (!tutor.isPresent()) 
            throw new IllegalArgumentException("Tutor não encontrado para o(s) pet(s) informado(s).");

        return petDTOs.stream()
                .map(petDTO -> petDTOToPet(petDTO, tutor.get()))
                .collect(Collectors.toList());
    }

    @Named("petListToPetDTOList")
    public List<PetDTO> petListToPetDTOList(List<Pet> pets) {
        if (pets == null)
            return Collections.emptyList();

        return pets.stream()
                .map(this::petToPetDTO)
                .collect(Collectors.toList());
    }
}
