package com.algaworks.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurante;

@Repository
public interface  RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante> {

	
	//@Query("from Restaurante r join r.cozinha left join fetch r.formasPagamento") // para evitar o problema de N+1 utilizando Eager Loaf.
	@Query("from Restaurante r join r.cozinha join fetch r.formasPagamento")
	List<Restaurante> findAll();
	
	List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInickal, BigDecimal taxafinal);
	Optional<Restaurante> findFirstByNomeContaining(String nome);
	List<Restaurante> findTop2ByNomeContaining(String nome);
	int countByCozinhaId(Long id);
	
	//List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);
	//@Query("from Restaurante where nome like %:nome% and cozinha.id = :id")  //JPQL -> Pode colocar no arquivo orm.xml (Named Queries)
	List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinhaId);

}
