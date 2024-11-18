package com.dadapetshop.registrations.service;

import org.springframework.stereotype.Service;

import com.dadapetshop.registrations.dto.UsuarioDTO;
import com.dadapetshop.registrations.mapper.UsuarioMapper;
import com.dadapetshop.registrations.repository.UsuarioRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioService {
    private UsuarioRepository usuarioRepository;
    private UsuarioMapper usuarioMapper;

    public void saveUsuario(UsuarioDTO usuarioDTO){
        if (usuarioRepository.findByEmail(usuarioDTO.email()).isPresent()) 
            throw new IllegalStateException ("Usuário com esse e-mail já existe.");

        var usuario = usuarioMapper.toEntityBeforeSave(usuarioDTO);
        usuarioRepository.save(usuario);
    }
}
