package br.com.fiap.gestanca.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.gestanca.models.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {
    Page<Despesa> findByDescricaoContaining(String busca, Pageable pageable);
    
    // @Query("select d from Despesa d order by d.id limit ?1 offset ?2")
    // List<Despesa> findTop(int range, int offset);
}
