package com.transicoes.demo.service;

import com.transicoes.demo.model.Transacao;
import com.transicoes.demo.dto.EstatisticasDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TransacaoService {

   private final List<Transacao> transacoes = Collections.synchronizedList(new ArrayList<>());


    public void adicionar(Transacao transacao) {
        transacoes.add(transacao);
    }

    public void limpar() {
        transacoes.clear();
    }

    public EstatisticasDTO getEstatisticas() {
        if (transacoes.isEmpty()) {
            return new EstatisticasDTO(0, 0, 0, 0, 0);
        }

        double soma = transacoes.stream().mapToDouble(Transacao::getValor).sum();
        double min = transacoes.stream().mapToDouble(Transacao::getValor).min().orElse(0);
        double max = transacoes.stream().mapToDouble(Transacao::getValor).max().orElse(0);
        long quantidade = transacoes.size();
        double media = soma / quantidade;

        return new EstatisticasDTO(soma, media, min, max, quantidade);
    }
}
