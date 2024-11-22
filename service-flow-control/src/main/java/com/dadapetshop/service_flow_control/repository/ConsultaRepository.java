package com.dadapetshop.service_flow_control.repository;

import com.dadapetshop.service_flow_control.model.Consulta;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.math.BigDecimal;
import java.util.Optional;

public interface ConsultaRepository extends MongoRepository<Consulta, String> {
    Optional<Consulta>findByCodigoFiscal(String codigoFiscal);
    Optional<Consulta>findByValor(BigDecimal valor);
}
