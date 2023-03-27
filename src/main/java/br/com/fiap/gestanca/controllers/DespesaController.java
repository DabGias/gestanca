package br.com.fiap.gestanca.controllers;

import java.util.List;
import java.util.Optional;

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

import br.com.fiap.gestanca.models.Despesa;
import br.com.fiap.gestanca.repositories.DespesaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/despesas")
public class DespesaController {
    Logger log = LoggerFactory.getLogger(DespesaController.class);

    @Autowired
    DespesaRepository repo;

    @GetMapping
    public List<Despesa> index() {
        return repo.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Despesa> show(@PathVariable Long id) {
        log.info("buscar despesa com id: " + id);

        Despesa despesa = repo.findById(id).orElseThrow(() -> 
            new ResponseStatusException(HttpStatus.NOT_FOUND, "despesa não encontrada") 
        );

        return ResponseEntity.ok(despesa);
    }

    @PostMapping
    public ResponseEntity<Despesa> create(@RequestBody @Valid Despesa despesa, BindingResult result) {
        log.info("cadastrar despesa: " + despesa);

        repo.save(despesa);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Despesa> update(@PathVariable Long id, @RequestBody @Valid Despesa despesaAtualizada) {
        log.info("a despesa com id: " + id + " foi atualizada");

        Despesa despesa = repo.findById(id).orElseThrow(() -> 
            new ResponseStatusException(HttpStatus.NOT_FOUND, "despesa não encontrada") 
        );

        BeanUtils.copyProperties(despesaAtualizada, despesa, "id");

        repo.save(despesa);

        return ResponseEntity.ok(despesa);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Despesa> destroy(@PathVariable Long id) {
        log.info("apagar despesa com id: " + id);

        Optional<Despesa> despesa = repo.findById(id);

        if (despesa.isEmpty()) return ResponseEntity.notFound().build();

        repo.delete(despesa.get());

        return ResponseEntity.noContent().build();
    }
}
