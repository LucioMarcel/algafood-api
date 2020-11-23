package com.algaworks.algafood.api.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteModel {
	
	private Long id;
	private String nome;
	private BigDecimal taxaFrete;
	private BigDecimal frete;
	private Boolean ativo;
	private CozinhaModel cozinha;
	private EnderecoModel endereco;
}
