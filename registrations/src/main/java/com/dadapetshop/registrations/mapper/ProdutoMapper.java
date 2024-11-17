package com.dadapetshop.registrations.mapper;

import org.mapstruct.Mapper;

import com.dadapetshop.registrations.dto.ProdutoDTO;
import com.dadapetshop.registrations.model.Produto;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {
    ProdutoDTO produtoToProdutoDTO(Produto produto);
    Produto produtoDToToProduto(ProdutoDTO produtoDTO); 
}
