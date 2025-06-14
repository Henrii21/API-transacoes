package com.transacoes.demo.controller;

import com.transacoes.demo.dto.EstatisticasDTO;
import com.transacoes.demo.dto.PeriodoDTO;
import com.transacoes.demo.dto.RequisicaoComBancoDTO;
import com.transacoes.demo.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/banco1")
public class Banco1Controller {

    @Autowired
    @Qualifier("banco1Service")
    private TransacaoService service;

    @PostMapping("/transacao")
    public ResponseEntity<Void> adicionar(@RequestBody RequisicaoComBancoDTO transacao) {
        service.adicionarTransacao(transacao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/estatisticas")
    public EstatisticasDTO obterEstatisticas() {
        return service.obterEstatisticas();
    }

    @DeleteMapping("/transacao")
    public void limpar() {
        service.limpar();
    }

    @GetMapping("/transacao/periodo")
    public EstatisticasDTO estatisticasPorPeriodo(@RequestBody PeriodoDTO periodo) {
        return service.obterEstatisticasPorPeriodo(
                periodo.getDataInicial(), periodo.getDataFinal()
        );
    }

    @DeleteMapping("/transacao/periodo")
    public ResponseEntity<Void> excluirPorPeriodo(@RequestBody PeriodoDTO periodo) {
        boolean excluiu = service.excluirTransacoesPorPeriodo(
                periodo.getDataInicial(),
                periodo.getDataFinal(),
                "BD1-123" // senha do banco 1
        );
        return excluiu ? ResponseEntity.ok().build() : ResponseEntity.noContent().build();
    }
}
