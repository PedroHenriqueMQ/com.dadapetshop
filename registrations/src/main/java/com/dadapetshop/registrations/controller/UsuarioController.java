package com.dadapetshop.registrations.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dadapetshop.registrations.dto.UsuarioDTO;
import com.dadapetshop.registrations.service.UsuarioService;

@RestController
@RequestMapping("/user")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @Transactional
    @PostMapping("/create-account")
    public ResponseEntity<Void> saveUsuario(@RequestBody UsuarioDTO usuario) {
        System.out.println(usuario.pets());
        usuarioService.saveUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
        
}
