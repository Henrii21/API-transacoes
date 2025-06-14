package com.transacoes.demo.service;

import com.transacoes.demo.dto.EstatisticasDTO;
import com.transacoes.demo.dto.RequisicaoComBancoDTO;

import java.time.ZonedDateTime;

public interface TransacaoService {
    void adicionarTransacao(RequisicaoComBancoDTO dto);
    EstatisticasDTO obterEstatisticas();
    EstatisticasDTO obterEstatisticasPorPeriodo(ZonedDateTime inicio, ZonedDateTime fim);
    boolean excluirTransacoesPorPeriodo(ZonedDateTime inicio, ZonedDateTime fim, String senha);
    void limpar();
}
