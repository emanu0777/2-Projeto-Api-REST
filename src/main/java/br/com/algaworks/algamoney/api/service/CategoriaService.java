package br.com.algaworks.algamoney.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.algaworks.algamoney.api.model.Categoria;
import br.com.algaworks.algamoney.api.repository.CategoriaRepository;

@Service
public class CategoriaService {

	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria atualizarCategoria(Long codigo, Categoria categoria) {
		Categoria categoriaSalva = buscaCategoriaPorCodigo(codigo).get();
		BeanUtils.copyProperties(categoria, categoriaSalva, "codigo");
		
		return categoriaSalva;
		
	}
	
	public Optional<Categoria> buscaCategoriaPorCodigo(Long codigo){
		
		 Optional <Categoria> categoriaSalva = categoriaRepository.findById(codigo);
		 if(categoriaSalva.isEmpty()) {
			 throw new EmptyResultDataAccessException(1);
		 }
		 
		 return categoriaSalva;
	}
			
	
}
