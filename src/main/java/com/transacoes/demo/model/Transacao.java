package com.transacoes.demo.model;

import jakarta.validation.constraints.*;
import java.time.Instant;

public class Transacao {

    @NotNull @Positive
    private Double valor;

    @NotNull
    private Instant dataHora;

    @NotBlank
    private String nome;

    @NotBlank
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")
    private String cpf;

    public Transacao() {}

    public Transacao(Double valor, Instant dataHora, String nome, String cpf) {
        this.valor = valor;
        this.dataHora = dataHora;
        this.nome = nome;
        this.cpf = cpf;
    }

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }

    public Instant getDataHora() { return dataHora; }
    public void setDataHora(Instant dataHora) { this.dataHora = dataHora; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
}
