package com.dadapetshop.service_flow_control.repository;

import com.dadapetshop.service_flow_control.model.Compra;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.math.BigDecimal;
import java.util.Optional;

public interface CompraRepository extends MongoRepository<Compra, String> {
    Optional<Compra>findByCodigoFiscal(String codigoFiscal);
    Optional<Compra>findByValorTotal(BigDecimal valor);
}
