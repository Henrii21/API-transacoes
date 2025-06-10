package com.transicoes.demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransacaoService {

    private final List<Transacao> transacoes = new ArrayList<>();

    public void adicionar(Transacao transacao) {
        transacoes.add(transacao);
    }

    public void limpar() {
        transacoes.clear();
    }

    public Estatisticas getEstatisticas() {
        double soma = 0;
        for (Transacao t : transacoes) {
            soma += t.getValor();
        }
        double media = transacoes.isEmpty() ? 0 : soma / transacoes.size();
        return new Estatisticas(soma, media, transacoes.size());
    }

    public static class Estatisticas {
        public double soma;
        public double media;
        public int quantidade;

        public Estatisticas(double soma, double media, int quantidade) {
            this.soma = soma;
            this.media = media;
            this.quantidade = quantidade;
        }
    }
}

