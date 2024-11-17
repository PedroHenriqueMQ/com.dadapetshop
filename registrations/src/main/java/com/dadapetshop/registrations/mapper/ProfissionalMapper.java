package com.dadapetshop.registrations.mapper;

import org.mapstruct.Mapper;

import com.dadapetshop.registrations.dto.ProfissionalDTO;
import com.dadapetshop.registrations.model.Profissional;

@Mapper(componentModel = "spring")
public interface ProfissionalMapper {
    ProfissionalDTO profissionalToProfissionalDTO(Profissional profissional);
    Profissional profissionalDTOToProfissional(ProfissionalDTO profissionalDTO); 
}
