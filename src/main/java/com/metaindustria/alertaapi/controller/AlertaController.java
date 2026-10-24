package com.metaindustria.alertaapi.controller;

import com.metaindustria.alertaapi.model.Alerta;
import com.metaindustria.alertaapi.service.AlertaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/alertas")
public class AlertaController {

    @Autowired
    private AlertaService alertaService;

    @PostMapping
    public ResponseEntity<Alerta> criar(@Valid @RequestBody Alerta alerta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(alertaService.criar(alerta));
    }

    @GetMapping
    public ResponseEntity<List<Alerta>> listarTodos() {
        return ResponseEntity.ok(alertaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alerta> buscarPorId(@PathVariable Long id) {
        return alertaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alerta> atualizar(@PathVariable Long id, @Valid @RequestBody Alerta alerta) {
        return alertaService.atualizar(id, alerta)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return alertaService.deletar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Alerta>> listarPorStatus(@PathVariable String status) {
        return ResponseEntity.ok(alertaService.listarPorStatus(status));
    }

    @GetMapping("/severidade/{severidade}")
    public ResponseEntity<List<Alerta>> listarPorSeveridade(@PathVariable String severidade) {
        return ResponseEntity.ok(alertaService.listarPorSeveridade(severidade));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Alerta>> listarPorTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(alertaService.listarPorTipo(tipo));
    }
}
