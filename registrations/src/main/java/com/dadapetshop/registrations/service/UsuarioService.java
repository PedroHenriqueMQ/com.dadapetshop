package com.dadapetshop.registrations.service;

import com.dadapetshop.registrations.dto.PetDTO;
import com.dadapetshop.registrations.exception.PetDuplicadoException;
import com.dadapetshop.registrations.exception.PetNaoEncontradoException;
import com.dadapetshop.registrations.exception.UsuarioEmailDuplicadoException;
import com.dadapetshop.registrations.exception.UsuarioNaoEncontradoException;
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
                throw new UsuarioEmailDuplicadoException();

        var usuario = usuarioMapper.toEntityBeforeSave(usuarioDTO);
        usuarioRepository.save(usuario);
    }

    public void addPet(UsuarioDTO usuarioDTO) {
        var usuarioEncontrado = usuarioRepository.findByEmail(usuarioDTO.email())
                .orElseThrow(UsuarioNaoEncontradoException::new);

        var usuarioMapeado = usuarioMapper.toEntityAfterSave(usuarioDTO);
        usuarioMapeado.getPets()
                .forEach(novoPet -> {
                    for(int i = 0; i < usuarioEncontrado.getPets().size(); i++) {
                        var petAtual = usuarioEncontrado.getPets().get(i);

                        var condicaoDeAtualização =
                                petAtual.getNome().equals(novoPet.getNome()) &&
                                petAtual.getRaca().equals(novoPet.getRaca()) &&
                                petAtual.getIdade().equals(novoPet.getIdade()) &&
                                petAtual.getPeso().equals(novoPet.getPeso());

                        if (condicaoDeAtualização)
                            usuarioEncontrado.getPets().add(novoPet);
                        else
                            throw new PetDuplicadoException();
                    }
                });

        usuarioRepository.save(usuarioEncontrado);
    }

    public void updatePet(PetDTO petDTO, String nome, String raca, Integer idade, BigDecimal peso) {
        var usuario = usuarioRepository.findByEmail(petDTO.emailTutor())
                .orElseThrow(UsuarioNaoEncontradoException::new);

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
            throw new PetNaoEncontradoException();

        usuarioRepository.save(usuario);
    }

    public void deletePet(PetDTO petDTO) {
        var usuario = usuarioRepository.findByEmail(petDTO.emailTutor())
                .orElseThrow(UsuarioNaoEncontradoException::new);

        var petFoiEncontrado = false;

        for(int i = 0; i < usuario.getPets().size(); i++) {
            var petAtual = usuario.getPets().get(i);

            var condicaoDeExclusao =
                    petAtual.getNome().equals(petDTO.nome()) &&
                            petAtual.getRaca().equals(petDTO.raca()) &&
                            petAtual.getIdade().equals(petDTO.idade()) &&
                            petAtual.getPeso().equals(petDTO.peso());

            if (condicaoDeExclusao) {
                petFoiEncontrado = true;

                usuario.getPets().remove(i);
                usuarioRepository.save(usuario);
            }
        }

        if (!petFoiEncontrado)
            throw new PetNaoEncontradoException();

        usuarioRepository.save(usuario);
    }
}
