package br.com.algaworks.algamoney.api.resource;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.algaworks.algamoney.api.event.RecursoCriadoEvent;
import br.com.algaworks.algamoney.api.model.Lancamento;
import br.com.algaworks.algamoney.api.repository.LancamentoRepository;
import br.com.algaworks.algamoney.api.service.LancamentoService;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@GetMapping
	public Page<Lancamento> listarLancamentos(@RequestParam(required = false) String Data, @RequestParam int pagina, 
			@RequestParam int qtd){
		
		Pageable paginacao = PageRequest.of(pagina, qtd); 
		
		if(Data == null) {
			Page<Lancamento> lancamentos =  lancamentoRepository.findAll(paginacao);
			return ResponseEntity.ok(lancamentos).getBody();
		}
		
		LocalDate dataConvertida = LocalDate.parse(Data);
		
		return lancamentoRepository.buscaLancamentoPorData(dataConvertida,paginacao);
		
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Lancamento> buscarLancamentoPorCodigo(@PathVariable Long codigo){
		Optional<Lancamento> lancamentoSalvo = lancamentoRepository.findById(codigo);
	
		if(lancamentoSalvo.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(lancamentoSalvo.get());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Lancamento> salvaLancamento(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response){
		Lancamento lancamentoRetornado = lancamentoService.verificaSalvamento(lancamento);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoRetornado.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoRetornado);
		
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Lancamento> deletaLacanamento(@PathVariable Long codigo){
		lancamentoRepository.deleteById(codigo);
		return ResponseEntity.noContent().build();
	}
	
}