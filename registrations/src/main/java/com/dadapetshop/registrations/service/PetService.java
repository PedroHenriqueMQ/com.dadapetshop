package com.dadapetshop.registrations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dadapetshop.registrations.dto.PetDTO;
import com.dadapetshop.registrations.mapper.PetMapper;
import com.dadapetshop.registrations.model.Pet;
import com.dadapetshop.registrations.repository.PetRepository;

import lombok.AllArgsConstructor;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private PetMapper petMapper;

    public void save(PetDTO petDTO) {
        var pet = petMapper.toEntity(petDTO);
        petRepository.save(pet);
    }
}
