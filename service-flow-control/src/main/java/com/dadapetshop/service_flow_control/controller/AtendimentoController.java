package com.dadapetshop.service_flow_control.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dadapetshop.service_flow_control.dto.ProcedimentoDTO;
import com.dadapetshop.service_flow_control.service.AtendimentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/service")
public class AtendimentoController {
    @Autowired
    private AtendimentoService atendimentoService;

    @PostMapping("/validate-service")
    public ResponseEntity<String> validarProcedimento(@Valid @RequestBody Object atendimentoDTO) {
        atendimentoService.validarAtendimento(atendimentoDTO);
        return ResponseEntity.ok("Validação enviada com sucesso!");
    }
}
