package com.dadapetshop.registrations.controller;

import com.dadapetshop.registrations.dto.PetDTO;
import com.dadapetshop.registrations.dto.UsuarioLoginDTO;
import com.dadapetshop.registrations.security.AccountDetailsHolder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.dadapetshop.registrations.dto.UsuarioDTO;
import com.dadapetshop.registrations.service.UsuarioService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final AccountDetailsHolder accountDetailsHolder;

    @Transactional
    @PostMapping("/create-account")
    public ResponseEntity<String> saveUsuario(@Valid @RequestBody UsuarioDTO usuario) {
        usuarioService.saveUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso!");
    }

    @Transactional
    @PatchMapping("/add-pet")
    public ResponseEntity<String> addPet(@Valid @RequestBody UsuarioDTO usuario) {
        if (!accountDetailsHolder.isUserAuthenticated(usuario.email()))
            return ResponseEntity.badRequest().body("Usuário não autenticado!");

        usuarioService.addPet(usuario);
        return ResponseEntity.status(HttpStatus.OK).body("Pet adicionado ao usuário com sucesso!");
    }

    @Transactional
    @PatchMapping("/update-pet")
    public ResponseEntity<String> updatePet(
            @RequestBody PetDTO petDTO,
            @RequestParam String nome,
            @RequestParam String raca,
            @RequestParam Integer idade,
            @RequestParam BigDecimal peso
            ) {
        if (!accountDetailsHolder.isUserAuthenticated(petDTO.emailTutor()))
            return ResponseEntity.badRequest().body("Usuário não autenticado!");

        usuarioService.updatePet(petDTO, nome, raca, idade, peso);
        return ResponseEntity.ok("Pet atualizado com sucesso!");
    }

    @Transactional
    @DeleteMapping("/delete-pet")
    public ResponseEntity<String>deletePet(@Valid @RequestBody PetDTO petDTO) {
        if (!accountDetailsHolder.isUserAuthenticated(petDTO.emailTutor()))
            return ResponseEntity.badRequest().body("Usuário não autenticado!");

        usuarioService.deletePet(petDTO);
        return ResponseEntity.ok("Pet removido com sucesso!");
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> handleLogin(@Valid @RequestBody UsuarioLoginDTO usuarioLoginDTO) {
        try {
            Authentication usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(usuarioLoginDTO.email(), usuarioLoginDTO.senha());
            var authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return authentication.isAuthenticated() ? ResponseEntity.ok(true) : ResponseEntity.badRequest().body(false);
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body(false);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(false);
        }
    }

}
