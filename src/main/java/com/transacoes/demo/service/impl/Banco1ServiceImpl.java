package com.transacoes.demo.service.impl;

import com.transacoes.demo.dto.EstatisticasDTO;
import com.transacoes.demo.dto.RequisicaoComBancoDTO;
import com.transacoes.demo.model.Transacao;
import com.transacoes.demo.service.TransacaoService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Service("banco1Service")
public class Banco1ServiceImpl implements TransacaoService {

    protected final List<Transacao> transacoes = new CopyOnWriteArrayList<>();

    @Override
    public void adicionarTransacao(RequisicaoComBancoDTO dto) {
        Transacao t = new Transacao(dto.getValor(), dto.getDataHora(), dto.getNome(), dto.getCpf());
        if (t.getValor() >= 5.0) {
            transacoes.add(t);
        }
    }

    @Override
    public EstatisticasDTO obterEstatisticas() {
        return calcular(transacoes);
    }

    @Override
    public EstatisticasDTO obterEstatisticasPorPeriodo(ZonedDateTime inicio, ZonedDateTime fim) {
        return calcular(filtrarPorPeriodo(inicio, fim));
    }

    @Override
    public boolean excluirTransacoesPorPeriodo(ZonedDateTime inicio, ZonedDateTime fim, String senha) {
        if (!"BD1-123".equals(senha)) return false;

        Instant i = inicio.toInstant();
        Instant f = fim.toInstant();
        return transacoes.removeIf(t -> {
            Instant d = t.getDataHora();
            return !d.isBefore(i) && !d.isAfter(f);
        });
    }

    @Override
    public void limpar() {
        transacoes.clear();
    }

    private EstatisticasDTO calcular(List<Transacao> lista) {
        double soma = lista.stream().mapToDouble(Transacao::getValor).sum();
        double media = lista.isEmpty() ? 0.0 : soma / lista.size();
        double max = lista.stream().mapToDouble(Transacao::getValor).max().orElse(0.0);
        double min = lista.stream().mapToDouble(Transacao::getValor).min().orElse(0.0);
        long count = lista.size();
        return new EstatisticasDTO(soma, media, max, min, count);
    }

    private List<Transacao> filtrarPorPeriodo(ZonedDateTime inicio, ZonedDateTime fim) {
        Instant i = inicio.toInstant();
        Instant f = fim.toInstant();
        return transacoes.stream()
            .filter(t -> !t.getDataHora().isBefore(i) && !t.getDataHora().isAfter(f))
            .collect(Collectors.toList());
    }
}
