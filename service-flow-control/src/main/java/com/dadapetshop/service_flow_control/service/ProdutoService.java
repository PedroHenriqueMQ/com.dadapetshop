package com.dadapetshop.service_flow_control.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dadapetshop.service_flow_control.client.IRegistrationsClient;
import com.dadapetshop.service_flow_control.dto.ProdutoDTO;

@Service
public class ProdutoService {
    @Autowired
    IRegistrationsClient registrationsClient;

    public ProdutoDTO getProdutoDTOs(String codigo) {
        return registrationsClient.getProduto(codigo);
    }
    
}
