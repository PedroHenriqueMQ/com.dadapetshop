package com.dadapetshop.registrations.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dadapetshop.registrations.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    
}
