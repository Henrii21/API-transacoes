package com.transicoes.demo;

import com.transicoes.demo.dto.EstatisticasDTO;
import com.transicoes.demo.model.Transacao;
import com.transicoes.demo.service.TransacaoService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransacaoController {

    @Autowired
    private TransacaoService service;

    @PostMapping("/transacao")
public ResponseEntity<Void> adicionarTransacao(@Valid @RequestBody Transacao transacao) {
    service.adicionar(transacao);
    return ResponseEntity.status(HttpStatus.CREATED).build();
}


    @GetMapping("/estatisticas")
    public EstatisticasDTO getEstatisticas() {
        return service.getEstatisticas();
    }

    @DeleteMapping("/transacao")
    public void limparTransacoes() {
        service.limpar();
    }
}
