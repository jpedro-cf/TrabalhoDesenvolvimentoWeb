package com.github.jpedrocf.trabalhowebdev;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TarefaRepository extends JpaRepository<Tarefa,Long> {
    Optional<Tarefa> findByResponsavel(String responsavel);
}
