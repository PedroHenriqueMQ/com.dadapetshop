package com.dadapetshop.service_flow_control.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dadapetshop.service_flow_control.dto.ProcedimentoDTO;
import com.dadapetshop.service_flow_control.service.ProcedimentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/procedure")
public class ProcedimentoController {
    @Autowired
    private ProcedimentoService procedimentoService;

    @PostMapping("/validate-procedure")
    public ResponseEntity<String> validarProcedimento(@Valid @RequestBody ProcedimentoDTO procedimentoDTO) {
        procedimentoService.validarProcedimento(procedimentoDTO);
        return ResponseEntity.ok("Validação enviada com sucesso!");
    }
}
