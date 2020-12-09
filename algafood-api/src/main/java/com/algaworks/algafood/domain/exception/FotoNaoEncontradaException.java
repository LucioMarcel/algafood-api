package com.algaworks.algafood.domain.exception;

public class FotoNaoEncontradaException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public FotoNaoEncontradaException(Long restauranteId, Long produtoId) {
		super(String.format("Não existe foto cadastrada para o produto  de código %d "
				+ "no restaurante de  código %d.", produtoId, restauranteId));
	}
	
}
