package com.transacoes.demo.service;

import com.transacoes.demo.model.Transacao;
import com.transacoes.demo.dto.EstatisticasDTO;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Service
public class TransacaoService {

    private final List<Transacao> transacoes = new CopyOnWriteArrayList<>();

    public void adicionarTransacao(Transacao transacao) {
        transacoes.add(transacao);
    }

    public EstatisticasDTO calcularEstatisticas() {
        Instant agora = Instant.now();
        Instant limite = agora.minusSeconds(60);

        List<Transacao> ultimos60s = transacoes.stream()
            .filter(t -> t.getDataHora().isAfter(limite))
            .collect(Collectors.toList());

        return calcularEstatisticas(ultimos60s);
    }

    public EstatisticasDTO calcularEstatisticas(List<Transacao> transacoesFiltradas) {
        double soma = transacoesFiltradas.stream().mapToDouble(Transacao::getValor).sum();
        double media = transacoesFiltradas.isEmpty() ? 0.0 : soma / transacoesFiltradas.size();
        double max = transacoesFiltradas.stream().mapToDouble(Transacao::getValor).max().orElse(0.0);
        double min = transacoesFiltradas.stream().mapToDouble(Transacao::getValor).min().orElse(0.0);
        long count = transacoesFiltradas.size();

        return new EstatisticasDTO(soma, media, max, min, count);
    }

    public void limpar() {
        transacoes.clear();
    }

    public EstatisticasDTO obterEstatisticasPorPeriodo(ZonedDateTime inicio, ZonedDateTime fim) {
        Instant inicioInstant = inicio.toInstant();
        Instant fimInstant = fim.toInstant();

        List<Transacao> transacoesFiltradas = transacoes.stream()
            .filter(t -> !t.getDataHora().isBefore(inicioInstant) && !t.getDataHora().isAfter(fimInstant))
            .collect(Collectors.toList());

        return calcularEstatisticas(transacoesFiltradas);
    }

    public boolean excluirTransacoesPorPeriodo(ZonedDateTime inicio, ZonedDateTime fim) {
    if (inicio == null || fim == null) return false;

    Instant inicioInstant = inicio.toInstant();
    Instant fimInstant = fim.toInstant();

    return transacoes.removeIf(transacao -> {
        if (transacao == null || transacao.getDataHora() == null) return false;

        Instant data = transacao.getDataHora();
        return !data.isBefore(inicioInstant) && !data.isAfter(fimInstant);
    });
}

}


