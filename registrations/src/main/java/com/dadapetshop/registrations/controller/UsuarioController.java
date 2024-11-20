package com.dadapetshop.registrations.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.dadapetshop.registrations.dto.UsuarioDTO;
import com.dadapetshop.registrations.service.UsuarioService;

@RestController
@RequestMapping("/user")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @Transactional
    @PostMapping("/create-account")
    public ResponseEntity<String> saveUsuario(@RequestBody UsuarioDTO usuario) {
        usuarioService.saveUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso!");
    }

    @Transactional
    @PatchMapping("/add-pet")
    public ResponseEntity<String> addPet(@RequestBody UsuarioDTO usuario) {
        usuarioService.addPet(usuario);
        return ResponseEntity.status(HttpStatus.OK).body("Pet adicionado ao usuário com sucesso!");
    }
}
