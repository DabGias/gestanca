package br.com.fiap.gestanca.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.gestanca.models.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {
    List<Despesa> findByData(LocalDate data);
}
