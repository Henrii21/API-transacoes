package com.transacoes.demo;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.transacoes.demo.dto.EstatisticasDTO;
import com.transacoes.demo.dto.PeriodoDTO;
import com.transacoes.demo.model.Transacao;
import com.transacoes.demo.service.TransacaoService;

@RestController
public class TransacaoController {

    @Autowired
    private TransacaoService service;

    @PostMapping("/transacao")
public ResponseEntity<Void> adicionarTransacao(@Valid @RequestBody Transacao transacao) {
    service.adicionarTransacao(transacao);
    return ResponseEntity.status(HttpStatus.CREATED).build();
}


    @GetMapping("/estatisticas")
    public EstatisticasDTO getEstatisticas() {
        return service.calcularEstatisticas();

    }

    @DeleteMapping("/transacao")
    public void limparTransacoes() {
        service.limpar();
    }

    @DeleteMapping("/transacao/periodo")
public ResponseEntity<Void> excluirTransacoesPorPeriodo(@RequestBody PeriodoDTO periodo) {
    boolean excluiu = service.excluirTransacoesPorPeriodo(

        periodo.getDataInicial(), periodo.getDataFinal()
    );
    return excluiu ? ResponseEntity.ok().build() : ResponseEntity.noContent().build();
}


}
