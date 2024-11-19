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

    public void saveUsuario(UsuarioDTO usuarioDTO) {
        if(usuarioRepository.findByEmail(usuarioDTO.email()).isPresent())
                throw new IllegalStateException("Usuário com esse e-mail já existe.");

        var usuario = usuarioMapper.toEntityBeforeSave(usuarioDTO);
        usuarioRepository.save(usuario);
    }

    public void addPet(UsuarioDTO usuarioDTO) {
        var usuarioEncontrado = usuarioRepository.findByEmail(usuarioDTO.email())
                .orElseThrow(() -> new IllegalStateException("Usuário não encontrado."));

        var usuarioMapeado = usuarioMapper.toEntityAfterSave(usuarioDTO);
        usuarioMapeado.getPets()
                .forEach(novoPet -> usuarioEncontrado.getPets().add(novoPet));

        usuarioRepository.save(usuarioEncontrado);
    }
}
