package com.metaindustria.alertaapi.service;

import com.metaindustria.alertaapi.model.Alerta;
import com.metaindustria.alertaapi.repository.AlertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AlertaService {

    @Autowired
    private AlertaRepository alertaRepository;

    public Alerta criar(Alerta alerta) {
        alerta.setId(null); // garante INSERT, nunca sobrescreve um registro existente
        return alertaRepository.save(alerta);
    }

    public List<Alerta> listarTodos() {
        return alertaRepository.findAll();
    }

    public Optional<Alerta> buscarPorId(Long id) {
        return alertaRepository.findById(id);
    }

    public Optional<Alerta> atualizar(Long id, Alerta dados) {
        return alertaRepository.findById(id).map(existente -> {
            existente.setTitulo(dados.getTitulo());
            existente.setDescricao(dados.getDescricao());
            existente.setSeveridade(dados.getSeveridade());
            existente.setOrigem(dados.getOrigem());
            existente.setLocalizacao(dados.getLocalizacao());
            existente.setTipo(dados.getTipo());
            if (dados.getStatus() != null && !dados.getStatus().isBlank()) {
                existente.setStatus(dados.getStatus());
            }
            existente.setAcaoTomada(dados.getAcaoTomada());
            if ("RESOLVIDO".equalsIgnoreCase(existente.getStatus())
                    && existente.getDataResolucao() == null) {
                existente.setDataResolucao(LocalDateTime.now());
            }
            return alertaRepository.save(existente);
        });
    }

    public boolean deletar(Long id) {
        if (alertaRepository.existsById(id)) {
            alertaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Alerta> listarPorStatus(String status) {
        return alertaRepository.findByStatus(status.toUpperCase());
    }

    public List<Alerta> listarPorSeveridade(String severidade) {
        return alertaRepository.findBySeveridade(severidade.toUpperCase());
    }

    public List<Alerta> listarPorTipo(String tipo) {
        return alertaRepository.findByTipo(tipo.toUpperCase());
    }
}
