package com.algaworks.algafood.api.controller;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@RestController
@RequestMapping("/teste")
public class TesteController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@GetMapping("/cozinhas/por-nome")
	public List<Cozinha> cozinhasPorNome(String nome){
		return cozinhaRepository.findTodasByNomeContaining(nome);
	}
	
	@GetMapping("/cozinhas/existe-por-nome")
	public Boolean esxisteCozinhaPorNome(String nome){
		return cozinhaRepository.existsByNome(nome);
	}

	@GetMapping("/cozinhas/unico-por-nome")
	public Optional<Cozinha> cozinhaPorNome(String nome){
		return cozinhaRepository.findByNome(nome);
	}
	
	@GetMapping("/restaurantes/por-taxa-frete")
	public List<Restaurante> restaurtantesPorTaxaFrete(BigDecimal taxaInicial, BigDecimal taxaFinal){
		return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
	}
	
	@GetMapping("/restaurantes/por-nome")
	public List<Restaurante> restaurtantesPorNome(String nome, Long cozinhaId){
		//return restauranteRepository.findByNomeContainingAndCozinhaId(nome, cozinhaId);
		return restauranteRepository.consultarPorNome(nome, cozinhaId);
	}
	
	@GetMapping("/restaurantes/por-nome-e-frete")
	public List<Restaurante> restaurtantesPorNomeFrete(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal){
		return restauranteRepository.find(nome, taxaInicial, taxaFinal);
	}
	
	@GetMapping("/restaurantes/primeiro-por-nome")
	public Optional<Restaurante> restaurtantePrimeiroPorNome(String nome){
		return restauranteRepository.findFirstByNomeContaining(nome);
	}
	
	@GetMapping("/restaurantes/primeiros-dois-por-nome")
	public List<Restaurante> restaurtantePrimeirosDoisPorNome(String nome){
		return restauranteRepository.findTop2ByNomeContaining(nome);
	}
	
	@GetMapping("/restaurantes/quantos-por-cozinha")
	public int  restaurtanteContar(Long id){
		return restauranteRepository.countByCozinhaId(id);
	}
	
	@GetMapping("/restaurantes/com-frete-gratis")
	public List<Restaurante> restaurtantesPComFreteGratis(String nome){
		
//		return restauranteRepository.findAll(comFreteGratis.and(comNomeSemelhante));
		return restauranteRepository.findComFreteGratis(nome);
	}
	
	@GetMapping("/restaurantes/buscar-primeiro")
	public Optional<Restaurante> restaurtantesBuscarPrimeiro(String nome){
		return restauranteRepository.buscarPrimeiro();
	}
	
	@GetMapping("/cozinhas/buscar-primeiro")
	public Optional<Cozinha> CozinhaBuscarPrimeiro(String nome){
		return cozinhaRepository.buscarPrimeiro();
	}
	
}
