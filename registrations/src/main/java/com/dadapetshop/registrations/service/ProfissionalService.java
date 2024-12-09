package com.dadapetshop.registrations.service;

import com.dadapetshop.registrations.exception.ProfissionalCPFDuplicadoException;
import com.dadapetshop.registrations.model.Profissional;
import org.springframework.stereotype.Service;

import com.dadapetshop.registrations.dto.ProfissionalDTO;
import com.dadapetshop.registrations.mapper.ProfissionalMapper;
import com.dadapetshop.registrations.repository.ProfissionalRepository;

import lombok.AllArgsConstructor;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfissionalService {
    private ProfissionalRepository profissionalRepository;
    private ProfissionalMapper profissionalMapper;

    public void saveProfissional(ProfissionalDTO profissionalDTO) {
        if(profissionalRepository.findByCpf(profissionalDTO.cpf()).isPresent())
                throw new ProfissionalCPFDuplicadoException();

        var profissional = profissionalMapper.toEntity(profissionalDTO);
        profissionalRepository.save(profissional);
    }

    public Optional<Profissional> findByCpf(String cpf) {
        return profissionalRepository.findByCpf(cpf);
    }
}