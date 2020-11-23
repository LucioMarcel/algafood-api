package com.algaworks.algafood;


import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;

@RunWith(SpringRunner.class)
//@SpringBootTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroCozinhaTestIT{
	
	@Autowired
	private CadastroCozinhaService cadastroCozinha;

	@Autowired
	private CadastroRestauranteService cadastrorestaurante;

	@Test public void deveAtribuirId_QuandoCdastrarCozinhaComDadosCorretos() { //
		//cenário 
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Chinesa");

		// ação
		novaCozinha = cadastroCozinha.salvar(novaCozinha);

		// validação
		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getId()).isNotNull();
	  
	  }

	@Test(expected = ConstraintViolationException.class)
	public void deveFalhar_QuandoCadastrarCozinhaSemNome() {
		Cozinha novacozinha = new Cozinha();
		novacozinha.setNome(null);

		cadastroCozinha.salvar(novacozinha);
	}

	@Test(expected = EntidadeEmUsoException.class)
	public void deveFalhar_QuandoExcluirCozinhaEmUso() {
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Chinesa");

		Restaurante novoRestaurante = new Restaurante();
		novoRestaurante.setNome("Benne Manjare");
		novoRestaurante.setTaxaFrete(BigDecimal.valueOf(10.00));

		novaCozinha = cadastroCozinha.salvar(novaCozinha);

		novoRestaurante.setCozinha(novaCozinha);
		novoRestaurante = cadastrorestaurante.salvar(novoRestaurante);

		cadastroCozinha.excluir(novaCozinha.getId());
	}

	@Test(expected = EntidadeNaoEncontradaException.class)
	public void deveFalhar_QuandoExcluirCozinhaInexistente() {
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Chinesa");

		novaCozinha = cadastroCozinha.salvar(novaCozinha);

		cadastroCozinha.excluir(novaCozinha.getId());

		cadastroCozinha.excluir(novaCozinha.getId());
	}
	
}

