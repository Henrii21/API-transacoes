package com.transacoes.demo.controller;

import com.transacoes.demo.model.Transacao;
import com.transacoes.demo.repository.TransacaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/transacao")
public class TransacaoComBancoController {

    @Autowired
    private TransacaoRepository repository;

    @GetMapping
    public List<Transacao> listarTodos() {
        return repository.findAll();
    }

    @PostMapping
    public Transacao salvar(@RequestBody Transacao transacao) {
        return repository.save(transacao);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void excluirTodos() {
        repository.deleteAll();
    }

    @GetMapping("/cpf")
    public List<Transacao> buscarPorCpf(@RequestParam String valor) {
        return repository.findByCpf(valor);
    }

    @GetMapping("/nome")
    public List<Transacao> buscarPorNome(@RequestParam String valor) {
        return repository.findByNomeContainingIgnoreCase(valor);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Transacao> atualizarPorId(@PathVariable Integer id, @RequestBody Transacao novaTransacao) {
        return repository.findById(id).map(transacao -> {
            transacao.setNome(novaTransacao.getNome());
            transacao.setCpf(novaTransacao.getCpf());
            transacao.setBanco(novaTransacao.getBanco());
            repository.save(transacao);
            return ResponseEntity.ok(transacao);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/periodo")
    @PreAuthorize("hasRole('ADMIN')")
    public void excluirPorPeriodo() {
    }
}
