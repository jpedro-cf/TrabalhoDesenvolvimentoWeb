package com.github.jpedrocf.trabalhowebdev;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/tarefas")
public class TarefaController {
    private final TarefaRepository tarefaRepository;

    public TarefaController(TarefaRepository tarefaRepository){
        this.tarefaRepository = tarefaRepository;
    }

    @PostMapping
    public ResponseEntity<Tarefa> create(@RequestBody Tarefa tarefa){
        Tarefa result = tarefaRepository.save(tarefa);

        return ResponseEntity.ok(result);
    }

    @GetMapping("{id}")
    public ResponseEntity<Tarefa> get(@PathVariable long id){
        return tarefaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> getAll(){
        return ResponseEntity.ok(tarefaRepository.findAll());
    }

    @PutMapping("{id}")
    public ResponseEntity<Tarefa> update(@PathVariable long id,
                                         @RequestBody Tarefa request){
        Optional<Tarefa> tarefaOpt = tarefaRepository.findById(id);
        if(tarefaOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Tarefa tarefa = tarefaOpt.get();

        tarefa.setNome(request.getNome());
        tarefa.setDataEntrega(request.getDataEntrega());
        tarefa.setResponsavel(request.getResponsavel());

        Tarefa updated = tarefaRepository.save(tarefa);

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable long id){
        Optional<Tarefa> tarefaOpt = tarefaRepository.findById(id);

        if (tarefaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Tarefa tarefa = tarefaOpt.get();

        tarefaRepository.delete(tarefa);

        String response = String.format(
                "Tarefa '%s' foi deletada com sucesso. Responsável pela tarefa: %s",
                tarefa.getNome(), tarefa.getResponsavel()
        );

        return ResponseEntity.ok(response);
    }
}
