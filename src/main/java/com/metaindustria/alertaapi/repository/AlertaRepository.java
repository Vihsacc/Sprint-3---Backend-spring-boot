package com.metaindustria.alertaapi.repository;

import com.metaindustria.alertaapi.model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Long> {
    List<Alerta> findByStatus(String status);
    List<Alerta> findBySeveridade(String severidade);
    List<Alerta> findByTipo(String tipo);
}
