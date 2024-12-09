package com.dadapetshop.service_flow_control.repository;
import com.dadapetshop.service_flow_control.model.Procedimento;
import org.springframework.cglib.core.Local;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public interface ProcedimentoRepository extends MongoRepository<Procedimento, String> {
    Optional<Procedimento> findByCodigoFiscal(String codigoFiscal);
    Optional<Procedimento> findByValor(BigDecimal valor);
    Optional<Procedimento> findByHorarioInicioBetween(LocalDateTime horarioInicio, LocalDateTime horarioTermino);
}
