package br.com.algaworks.algamoney.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.algaworks.algamoney.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	
	Optional <Usuario> findByEmail(String email);
	
	
	
}
