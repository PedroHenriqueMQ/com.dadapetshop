package com.dadapetshop.registrations.mapper;

import java.util.Collections;
import java.util.List;

import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dadapetshop.registrations.dto.PetDTO;
import com.dadapetshop.registrations.model.Pet;
import com.dadapetshop.registrations.model.Usuario;
import com.dadapetshop.registrations.repository.UsuarioRepository;

@Component
public class UsuarioMapperHelper {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Named("emailToUsuario")
    public Usuario emailToUsuario(String email) {
        if (email == null) throw new IllegalArgumentException("Usuário com email " + email + " não encontrado");
        
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));
    }

    @Named("usuarioToEmail")
    public String usuarioToEmail(Usuario usuario) {
        if (usuario == null) throw new IllegalArgumentException("Usuário não encontrado.");

        var usuarioEncontrado = usuarioRepository.findByEmail(usuario.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        return usuarioEncontrado.getEmail();
    }

    @Named("initializePets")
    public List<Pet> initializePets(List<PetDTO> petDTOs) {
        return Collections.emptyList();
    }
}
