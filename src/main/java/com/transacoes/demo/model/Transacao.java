package com.transacoes.demo.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.Instant;

public class Transacao {

    @NotNull
    @Positive
    private double valor;

    @NotNull
    private Instant dataHora;

    public Transacao() {}

    public Transacao(double valor, Instant dataHora) {
        this.valor = valor;
        this.dataHora = dataHora;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Instant getDataHora() {
        return dataHora;
    }

    public void setDataHora(Instant dataHora) {
        this.dataHora = dataHora;
    }
}
