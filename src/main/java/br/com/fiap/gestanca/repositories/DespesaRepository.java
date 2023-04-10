package br.com.fiap.gestanca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.gestanca.models.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {
    // @Query("select d from Despesa d order by d.id limit ?1 offset ?2")
    // List<Despesa> findTop(int range, int offset);
}
