package com.dadapetshop.service_flow_control.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dadapetshop.service_flow_control.dto.ProdutoDTO;
import com.dadapetshop.service_flow_control.service.ProdutoService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProdutoController {
    ProdutoService produtoService;

    @GetMapping("/codigo/{codigo}")
    public ProdutoDTO getProduto(@PathVariable String codigo) {
        return produtoService.getProdutoDTOs(codigo);
    }
    
}
