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
        return alertaRepository.save(alerta);
    }

    public List<Alerta> listarTodos() {
        return alertaRepository.findAll();
    }

    public Optional<Alerta> buscarPorId(Long id) {
        return alertaRepository.findById(id);
    }

    public Optional<Alerta> atualizar(Long id, Alerta dadosAtualizados) {
        return alertaRepository.findById(id).map(existente -> {
            existente.setTitulo(dadosAtualizados.getTitulo());
            existente.setDescricao(dadosAtualizados.getDescricao());
            existente.setSeveridade(dadosAtualizados.getSeveridade());
            existente.setOrigem(dadosAtualizados.getOrigem());
            existente.setLocalizacao(dadosAtualizados.getLocalizacao());
            existente.setTipo(dadosAtualizados.getTipo());
            existente.setStatus(dadosAtualizados.getStatus());
            existente.setAcaoTomada(dadosAtualizados.getAcaoTomada());
            if ("RESOLVIDO".equalsIgnoreCase(dadosAtualizados.getStatus())
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
