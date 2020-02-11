package br.com.algaworks.algamoney.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.algaworks.algamoney.api.model.Pessoa;
import br.com.algaworks.algamoney.api.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Pessoa atualizar(Long codigo, Pessoa pessoa) {
		Optional <Pessoa> pessoaSalva = buscaPessoaPeloCodigo(codigo);
		BeanUtils.copyProperties(pessoa, pessoaSalva.get(), "codigo");
		return pessoaSalva.get();
	}
	
	
	public Pessoa atualizarCampoAtivo(Long codigo, Boolean ativo){
		Pessoa pessoaSalva = buscaPessoaPeloCodigo(codigo).get();
		pessoaSalva.setAtivo(ativo);
		
		return pessoaSalva;
	}
	
	
	public Optional<Pessoa> buscaPessoaPeloCodigo(Long codigo) {
		
		Optional <Pessoa> pessoaSalva = pessoaRepository.findById(codigo);
		
		if(pessoaSalva.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return pessoaSalva;
	}
}
