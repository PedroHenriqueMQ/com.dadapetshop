package com.dadapetshop.service_flow_control.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dadapetshop.service_flow_control.dto.ConsultaDTO;
import com.dadapetshop.service_flow_control.service.ConsultaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/consultation")
public class ConsultaController {
    @Autowired
    private ConsultaService consultaService;

    @PostMapping("/validate-consultation")
    public ResponseEntity<String> validarConsulta(@Valid @RequestBody ConsultaDTO consultaDTO) {
        consultaService.validarConsulta(consultaDTO);
        return ResponseEntity.ok("Envida mensagem de validação de consulta!");
    }
}
