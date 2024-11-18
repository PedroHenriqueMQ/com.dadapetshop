package com.dadapetshop.registrations.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dadapetshop.registrations.model.Profissional;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
    
}
