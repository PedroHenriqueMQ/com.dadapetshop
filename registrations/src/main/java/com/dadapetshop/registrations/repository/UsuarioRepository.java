package com.dadapetshop.registrations.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dadapetshop.registrations.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Optional<Usuario> findByEmail(String email);
}
