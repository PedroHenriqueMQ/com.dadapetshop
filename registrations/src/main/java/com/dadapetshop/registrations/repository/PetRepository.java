package com.dadapetshop.registrations.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.dadapetshop.registrations.model.Pet;


public interface PetRepository extends JpaRepository<Pet, Long> {

    
}
