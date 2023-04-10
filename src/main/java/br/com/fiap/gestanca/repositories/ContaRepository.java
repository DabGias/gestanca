package br.com.fiap.gestanca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.gestanca.models.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {}
