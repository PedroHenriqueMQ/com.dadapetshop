package com.dadapetshop.registrations.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dadapetshop.registrations.dto.ProfissionalDTO;
import com.dadapetshop.registrations.service.ProfissionalService;

@RestController
@RequestMapping("/employee")
public class ProfissionalController {
    
    @Autowired
    ProfissionalService profissionalService;

    @Transactional
    @PostMapping("/register-employee")
    public ResponseEntity<String> saveProfissional(@Valid @RequestBody ProfissionalDTO profissional){
        profissionalService.saveProfissional(profissional);
        return ResponseEntity.status(HttpStatus.CREATED).body("Funcionário cadastrado com sucesso!");
    }
}
