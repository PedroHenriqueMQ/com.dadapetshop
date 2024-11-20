package com.dadapetshop.registrations.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.dadapetshop.registrations.model.Produto;
import com.dadapetshop.registrations.model.ProdutoCategoria;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Optional<Produto> findByCodigo(String codigo);
    Optional<Produto> findByCategoria(ProdutoCategoria categoria);
}
