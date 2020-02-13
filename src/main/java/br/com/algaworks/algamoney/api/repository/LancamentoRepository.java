package br.com.algaworks.algamoney.api.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.algaworks.algamoney.api.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
	
	@Query(value = "SELECT * FROM lancamento WHERE lancamento.data_vencimento =:Data", nativeQuery = true)
	Page<Lancamento> buscaLancamentoPorData(@Param("Data") LocalDate Data, Pageable paginacao); 
}
