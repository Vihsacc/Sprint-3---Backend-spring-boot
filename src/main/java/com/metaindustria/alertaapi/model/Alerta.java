package com.metaindustria.alertaapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "alertas")
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O titulo e obrigatorio")
    @Column(nullable = false, length = 150)
    private String titulo;

    @NotBlank(message = "A descricao e obrigatoria")
    @Column(nullable = false, length = 500)
    private String descricao;

    @NotBlank(message = "A severidade e obrigatoria")
    @Column(nullable = false, length = 20)
    private String severidade; // BAIXA, MEDIA, ALTA, CRITICA

    @NotBlank(message = "A origem e obrigatoria")
    @Column(nullable = false, length = 150)
    private String origem;

    @NotBlank(message = "A localizacao e obrigatoria")
    @Column(nullable = false, length = 150)
    private String localizacao;

    @NotBlank(message = "O tipo e obrigatorio")
    @Column(nullable = false, length = 50)
    private String tipo; // TEMPERATURA, PRESSAO, VIBRACAO, UMIDADE, FALHA_MECANICA

    @Column(nullable = false, length = 20)
    private String status; // ABERTO, EM_ANALISE, RESOLVIDO

    @Column(length = 300)
    private String acaoTomada;

    @Column(nullable = false)
    private LocalDateTime dataAbertura;

    private LocalDateTime dataResolucao;

    @PrePersist
    public void prePersist() {
        this.dataAbertura = LocalDateTime.now();
        if (this.status == null || this.status.isBlank()) {
            this.status = "ABERTO";
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getSeveridade() { return severidade; }
    public void setSeveridade(String severidade) { this.severidade = severidade; }

    public String getOrigem() { return origem; }
    public void setOrigem(String origem) { this.origem = origem; }

    public String getLocalizacao() { return localizacao; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getAcaoTomada() { return acaoTomada; }
    public void setAcaoTomada(String acaoTomada) { this.acaoTomada = acaoTomada; }

    public LocalDateTime getDataAbertura() { return dataAbertura; }
    public void setDataAbertura(LocalDateTime dataAbertura) { this.dataAbertura = dataAbertura; }

    public LocalDateTime getDataResolucao() { return dataResolucao; }
    public void setDataResolucao(LocalDateTime dataResolucao) { this.dataResolucao = dataResolucao; }
}
