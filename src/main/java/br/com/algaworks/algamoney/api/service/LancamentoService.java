package br.com.algaworks.algamoney.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.algaworks.algamoney.api.model.Lancamento;
import br.com.algaworks.algamoney.api.model.Pessoa;
import br.com.algaworks.algamoney.api.repository.LancamentoRepository;
import br.com.algaworks.algamoney.api.repository.PessoaRepository;
import br.com.algaworks.algamoney.api.service.exception.ExceptionPessoaInativaOuPessoaInexistente;

@Service
public class LancamentoService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private LancamentoRepository lancamentoRepostory;

	
	public Lancamento verificaSalvamento(Lancamento lancamento) {

		Optional<Pessoa> pessoaSalvaLancamento = pessoaRepository.findById(lancamento.getPesssoa().getCodigo());
		
		if(pessoaSalvaLancamento.isEmpty() || pessoaSalvaLancamento.get().isInativo()) {
			
			throw new ExceptionPessoaInativaOuPessoaInexistente();
		}
		
		return lancamentoRepostory.save(lancamento);
		
	}
	
}
