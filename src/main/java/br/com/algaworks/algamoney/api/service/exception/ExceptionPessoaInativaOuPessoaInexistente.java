package br.com.algaworks.algamoney.api.service.exception;

import java.util.Arrays;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.algaworks.algamoney.api.exceptionhandler.AlgamoneyExceptionHandler.Erro;

@ControllerAdvice
public class ExceptionPessoaInativaOuPessoaInexistente extends RuntimeException {

	private static final long serialVersionUID = 1L;

	
	@Autowired
	private MessageSource messageSource;
	

	@ExceptionHandler({ ExceptionPessoaInativaOuPessoaInexistente.class })
	public ResponseEntity<Object> handlerExceptionPessoaInativaOuPessoaInexistente(ExceptionPessoaInativaOuPessoaInexistente ex){
		
		String mensagemUsuario = messageSource.getMessage("pessoa.inativa-ou-inexistente", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor =  ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario,mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
		
	}
	
	
}
