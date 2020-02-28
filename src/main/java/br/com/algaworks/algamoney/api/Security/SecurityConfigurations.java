package br.com.algaworks.algamoney.api.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.algaworks.algamoney.api.repository.UsuarioRepository;


@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	
	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
			return super.authenticationManager();
	}
	
	
	//Configuração de Autenticação
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		                       // Diz ao Spring qual a service contém a lógica de autenticacao
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());

	}
	
	
	//Configurações de Autorização
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http.authorizeRequests()
		.antMatchers("/categorias").permitAll()
		.antMatchers("/auth").permitAll()//URL filtradada e a sua permissao. É possivel filtrar o tipo da requisicao tbm.
		.anyRequest().authenticated() // diz que qualquer requisicao fora /categorias, o usuario precisa estar logado
		.and().csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);
		
	}
	
	//Configuração de arquivos staticos(js,css, imagens,etc)
	@Override
	public void configure(WebSecurity web) throws Exception {
	 
		
	}
	
	
}
