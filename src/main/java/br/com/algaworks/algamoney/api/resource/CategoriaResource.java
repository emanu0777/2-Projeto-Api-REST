package br.com.algaworks.algamoney.api.resource;


import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.algaworks.algamoney.api.repository.CategoriaRepository;
import br.com.algaworks.algamoney.api.service.CategoriaService;
import br.com.algaworks.algamoney.api.event.RecursoCriadoEvent;
import br.com.algaworks.algamoney.api.model.Categoria;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	//injeção de dependencia 
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	private List<Categoria> listar(){
		return categoriaRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Categoria> adiciona(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {

		Categoria categoriaSalva = categoriaRepository.save(categoria);

		publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getCodigo()));

		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
		
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Categoria> buscarPeloCodigo(@PathVariable Long codigo) {
		
		Optional<Categoria> categoriaSalva = categoriaRepository.findById(codigo);
		
		
		return !categoriaSalva.isEmpty()? ResponseEntity.ok(categoriaSalva.get()) : ResponseEntity.notFound().build();
		
		
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletaCategoria(@PathVariable Long codigo){
		categoriaRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Categoria> atualizaCategoria(@PathVariable Long codigo, @Valid @RequestBody Categoria categoria){
		 Categoria categoriaSalva = categoriaService.atualizarCategoria(codigo, categoria);
		 categoriaRepository.save(categoriaSalva);
		 return ResponseEntity.noContent().build();		
	}
	
}
