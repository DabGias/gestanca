package br.com.fiap.gestanca.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.gestanca.models.Despesa;
import br.com.fiap.gestanca.repositories.DespesaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/despesas")
public class DespesaController {
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    DespesaRepository repo;

    @Autowired
    PagedResourcesAssembler<Despesa> assembler;

    @GetMapping
    public PagedModel<EntityModel<Despesa>> index(@PageableDefault(size = 5) Pageable pageable, @RequestParam(required = false) String busca) {        
        Page<Despesa> page = (busca == null) ? repo.findAll(pageable) : repo.findByDescricaoContaining(busca, pageable);

        return assembler.toModel(page);
    }

    @GetMapping("{id}")
    public EntityModel<Despesa> show(@PathVariable Long id) {
        log.info("buscar despesa com id: " + id);

        Despesa despesa = repo.findById(id).orElseThrow(() -> 
            new ResponseStatusException(HttpStatus.NOT_FOUND, "despesa não encontrada") 
        );

        return despesa.toModel();
    }

    @PostMapping
    public ResponseEntity<Despesa> create(@RequestBody @Valid Despesa despesa, BindingResult result) {
        log.info("cadastrar despesa: " + despesa);

        repo.save(despesa);

        return ResponseEntity.created(despesa.toModel().getRequiredLink("self").toUri()).body(despesa);
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

        Despesa despesa = repo.findById(id).orElseThrow(() -> 
            new ResponseStatusException(HttpStatus.NOT_FOUND, "despesa não encontrada") 
        );

        repo.delete(despesa);

        return ResponseEntity.noContent().build();
    }
}
