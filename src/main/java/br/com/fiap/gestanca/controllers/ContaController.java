package br.com.fiap.gestanca.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.gestanca.models.Conta;
import br.com.fiap.gestanca.repositories.ContaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/contas")
public class ContaController {
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    ContaRepository repo;

    @GetMapping
    public List<Conta> index() {
        return repo.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Conta> show(@PathVariable Long id) {
        log.info("buscar conta com id: " + id);

        Conta conta = getConta(id);

        return ResponseEntity.ok(conta);
    }

    @PostMapping
    public ResponseEntity<Conta> create(@RequestBody @Valid Conta conta, BindingResult result) {
        log.info("cadastrar conta: " + conta);

        repo.save(conta);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Conta> update(@PathVariable Long id, @RequestBody @Valid Conta contaAtualizada) {
        log.info("a conta com id: " + id + " foi atualizada");

        Conta conta = getConta(id);

        BeanUtils.copyProperties(contaAtualizada, conta, "id");

        repo.save(conta);

        return ResponseEntity.ok(conta);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Conta> destroy(@PathVariable Long id) {
        log.info("apagar conta com id: " + id);

        Conta conta = getConta(id);

        repo.delete(conta);

        return ResponseEntity.noContent().build();
    }

    private Conta getConta(Long id) {
        Conta conta = repo.findById(id).orElseThrow(() -> 
            new ResponseStatusException(HttpStatus.NOT_FOUND, "conta não encontrada") 
        );

        return conta;
    }
}
