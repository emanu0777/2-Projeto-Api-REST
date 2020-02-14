package br.com.algaworks.algamoney.api.Security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	//Configuração de Autenticação
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	}
	
	
	//Configurações de Autorização
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http.authorizeRequests()
		.antMatchers("/categorias").permitAll()
		.anyRequest().authenticated();
		
	}
	
	//Configuração de arquivos staticos(js,css, imagens,etc)
	@Override
	public void configure(WebSecurity web) throws Exception {
	 
		
	}
	
}
