package com.dadapetshop.registrations.service;

import org.springframework.stereotype.Service;

import com.dadapetshop.registrations.dto.ProfissionalDTO;
import com.dadapetshop.registrations.mapper.ProfissionalMapper;
import com.dadapetshop.registrations.repository.ProfissionalRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProfissionalService {
    private ProfissionalRepository profissionalRepository;
    private ProfissionalMapper profissionalMapper;

    public void saveProfissional(ProfissionalDTO profissionalDTO) {
        if(profissionalRepository.findByCpf(profissionalDTO.cpf()).isPresent())
                throw new IllegalStateException("Funcionário com esse CPF já existe.");

        var profissional = profissionalMapper.toEntity(profissionalDTO);
        profissionalRepository.save(profissional);
    }
}