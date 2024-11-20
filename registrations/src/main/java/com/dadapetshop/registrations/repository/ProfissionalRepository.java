package com.dadapetshop.registrations.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.dadapetshop.registrations.model.Profissional;
import java.util.Optional;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
    Optional<Profissional>findByCpf(String cpf);
}
