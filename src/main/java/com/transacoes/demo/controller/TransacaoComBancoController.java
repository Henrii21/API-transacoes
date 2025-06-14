package com.transacoes.demo.controller;

import com.transacoes.demo.dto.EstatisticasDTO;
import com.transacoes.demo.dto.RequisicaoComBancoDTO;
import com.transacoes.demo.service.TransacaoService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("/banco")
public class TransacaoComBancoController {

    @Autowired
    private ApplicationContext context;

    private TransacaoService getService(String banco) {
    return switch (banco.toUpperCase()) {
        case "BANCO1" -> (TransacaoService) context.getBean("banco1Service");
        case "BANCO2" -> (TransacaoService) context.getBean("banco2Service");
        case "BANCO3" -> (TransacaoService) context.getBean("banco3Service");
        default -> throw new IllegalArgumentException("Banco inválido: " + banco);
    };
}

@PostMapping("/transacao")
public ResponseEntity<Void> adicionar(@RequestBody RequisicaoComBancoDTO dto) {
    getService(dto.getBanco()).adicionarTransacao(dto);
    return ResponseEntity.status(HttpStatus.CREATED).build();
}


    @GetMapping("/estatisticas")
    public EstatisticasDTO estatisticas(@RequestParam String banco) {
        return getService(banco).obterEstatisticas();
    }

    @GetMapping("/transacao/periodo")
    public EstatisticasDTO estatisticasPorPeriodo(@RequestParam String banco,
                                                  @RequestParam ZonedDateTime inicio,
                                                  @RequestParam ZonedDateTime fim) {
        return getService(banco).obterEstatisticasPorPeriodo(inicio, fim);
    }

    @DeleteMapping("/transacao")
    public void limpar(@RequestParam String banco) {
        getService(banco).limpar();
    }

    @DeleteMapping("/transacao/periodo")
    public ResponseEntity<Void> excluirPorPeriodo(@RequestParam String banco,
                                                  @RequestParam ZonedDateTime inicio,
                                                  @RequestParam ZonedDateTime fim,
                                                  @RequestParam String senha) {
        boolean excluiu = getService(banco).excluirTransacoesPorPeriodo(inicio, fim, senha);
        return excluiu ? ResponseEntity.ok().build() : ResponseEntity.noContent().build();
    }
}
