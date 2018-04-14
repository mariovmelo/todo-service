package br.ufrn.ws.todoservicers.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.ws.todoservicers.dominio.Tarefa;
import br.ufrn.ws.todoservicers.repository.TarefaRepository;

@RestController
@RequestMapping("/tarefa")
public class TarefaResource {
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	private int pageSize = 2;
	
	@GetMapping(produces={"application/json","application/xml"})
	public List<Tarefa> listarTarefas(@RequestParam(required=false,defaultValue="descricao")
										String sort){
		return tarefaRepository.findAll(new Sort(Direction.ASC,sort));
	}
	
	@GetMapping("/limitada")
	public List<Tarefa> listarTarefasPaginada(@RequestParam int page,
			@RequestHeader("Authorization") String token){
		
		System.out.println("Token:"+token);
		
		Page<Tarefa> paginaTarefa = tarefaRepository.findAll(PageRequest.of(page, pageSize));
		
		return paginaTarefa.getContent();
	}
	
	@GetMapping("/{idTarefa}")
	public Tarefa listaPorId(@PathVariable Long idTarefa) {
		return tarefaRepository.findById(idTarefa).get();
	}
	
	
	@PostMapping(consumes="application/json",produces={"application/json","application/xml"})
	@ResponseStatus(code=HttpStatus.CREATED)
	public Tarefa inserir(@Valid @RequestBody Tarefa tarefa) {
		return tarefaRepository.save(tarefa);
	}
	
	@PutMapping
	@ResponseStatus(code=HttpStatus.OK)
	public Tarefa atualizar(@Valid @RequestBody Tarefa tarefa) {
		return tarefaRepository.save(tarefa);
	}
	
	@DeleteMapping("/{idTarefa}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void atualizar(@PathVariable Long idTarefa) {
		tarefaRepository.deleteById(idTarefa);
	}
}
