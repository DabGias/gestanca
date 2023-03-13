package br.com.fiap.gestanca.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.gestanca.models.Despesa;

@RestController
public class DespesaController {
    Logger log = LoggerFactory.getLogger(DespesaController.class);
    List<Despesa> despesas = new ArrayList<>();

    @GetMapping("/api/despesas")
    public List<Despesa> index() {
        return despesas;
    }

    @GetMapping("/api/despesas/{id}")
    public ResponseEntity<Despesa> show(@PathVariable Long id) {
        log.info("buscar despesa com id: " + id);

        Optional<Despesa> despesa = despesas.stream().filter((d) -> {
            return d.getId().equals(id);
        }).findFirst();

        if (despesa.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(despesa.get());
    }

    @PostMapping("/api/despesas")
    public ResponseEntity<Despesa> create(@RequestBody Despesa despesa) {
        log.info("cadastrar despesa: " + despesa);

        despesa.setId(despesas.size() + 1l);
        despesas.add(despesa);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/api/despesas/{id}")
    public ResponseEntity<Despesa> update(@PathVariable Long id, @RequestBody Despesa despesaAtualizada) {
        log.info("a despesa com id: " + id + " foi atualizada");

        Optional<Despesa> despesa = despesas.stream().filter((d) -> {
            return d.getId().equals(id);
        }).findFirst();

        if (despesa.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        despesas.remove(despesa.get());
        despesaAtualizada.setId(id);
        despesas.add(despesaAtualizada);

        return ResponseEntity.ok(despesa.get());
    }

    @DeleteMapping("/api/despesas/{id}")
    public ResponseEntity<Despesa> destroy(@PathVariable Long id) {
        log.info("apagar despesa com id: " + id);

        Optional<Despesa> despesa = despesas.stream().filter((d) -> {
            return d.getId().equals(id);
        }).findFirst();

        if (despesa.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        despesas.remove(despesa.get());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
