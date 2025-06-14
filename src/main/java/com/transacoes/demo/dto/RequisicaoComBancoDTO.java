package com.transacoes.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.time.Instant;

public class RequisicaoComBancoDTO {
    @Positive
    private Double valor;

    private Instant dataHora;

    @NotBlank
    private String nome;

    @NotBlank
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")
    private String cpf;

    @NotBlank
    private String banco;

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }

    public Instant getDataHora() { return dataHora; }
    public void setDataHora(Instant dataHora) { this.dataHora = dataHora; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getBanco() { return banco; }
    public void setBanco(String banco) { this.banco = banco; }
}
