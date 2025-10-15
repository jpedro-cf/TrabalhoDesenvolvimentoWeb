package com.github.jpedrocf.trabalhowebdev.repositories;

import com.github.jpedrocf.trabalhowebdev.models.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TarefaRepository extends JpaRepository<Tarefa,Long> {
    Optional<Tarefa> findByResponsavel(String responsavel);
}
