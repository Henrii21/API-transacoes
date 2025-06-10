package com.transicoes.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransacaoController {

    @Autowired
    private TransacaoService service;

    @PostMapping("/transacao")
    public void adicionarTransacao(@RequestBody Transacao transacao) {
        service.adicionar(transacao);
    }

    @GetMapping("/estatisticas")
    public TransacaoService.Estatisticas getEstatisticas() {
        return service.getEstatisticas();
    }

    @DeleteMapping("/transacao")
    public void limparTransacoes() {
        service.limpar();
    }
}
