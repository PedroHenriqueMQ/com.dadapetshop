package com.dadapetshop.registrations.mapper;

import org.mapstruct.Mapper;

import com.dadapetshop.registrations.dto.ProdutoDTO;
import com.dadapetshop.registrations.model.Produto;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {
    ProdutoDTO toDTO(Produto produto);
    @Mapping(target = "quantidadePrateleira", ignore = true)
    Produto toEntity(ProdutoDTO produtoDTO);
}