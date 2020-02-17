package br.com.algaworks.algamoney.api.resource;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.algaworks.algamoney.api.form.LoginForm;

@RestController
@RequestMapping("/auth")
public class AutenticacaoResource {

	@PostMapping
	public ResponseEntity<?> autenticar(@RequestBody @Valid LoginForm form){
		
		System.out.println(form.getEmail());
		System.out.println(form.getSenha());
		
		return ResponseEntity.ok().build();
		
		
	}
	
}
