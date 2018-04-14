package br.ufrn.ws.todoservicers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufrn.ws.todoservicers.dominio.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

}
