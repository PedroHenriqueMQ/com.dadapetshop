package com.dadapetshop.registrations.service;

import com.dadapetshop.registrations.dto.PetDTO;
import org.springframework.stereotype.Service;

import com.dadapetshop.registrations.dto.UsuarioDTO;
import com.dadapetshop.registrations.mapper.UsuarioMapper;
import com.dadapetshop.registrations.repository.UsuarioRepository;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

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

    public void updatePet(PetDTO petDTO, String nome, String raca, Integer idade, BigDecimal peso) {
        var usuario = usuarioRepository.findByEmail(petDTO.emailTutor())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        var petFoiEncontrado = false;

        for(int i = 0; i < usuario.getPets().size(); i++) {
            var petAtual = usuario.getPets().get(i);

            var condicaoDeAtualização =
                    petAtual.getNome().equals(nome) &&
                    petAtual.getRaca().equals(raca) &&
                    petAtual.getIdade().equals(idade) &&
                    petAtual.getPeso().equals(peso);

            if (condicaoDeAtualização) {
                petFoiEncontrado = true;

                usuario.getPets().get(i).setNome(petDTO.nome());
                usuario.getPets().get(i).setRaca(petDTO.raca());
                usuario.getPets().get(i).setIdade(petDTO.idade());
                usuario.getPets().get(i).setPeso(petDTO.peso());
            }
        }

        if (!petFoiEncontrado)
            throw new IllegalArgumentException("Pet não encontrado.");

        usuarioRepository.save(usuario);
    }
}
