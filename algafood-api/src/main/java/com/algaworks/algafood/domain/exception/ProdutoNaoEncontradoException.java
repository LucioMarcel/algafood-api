package com.algaworks.algafood.domain.exception;

public class ProdutoNaoEncontradoException extends EntidadeEmUsoException{

	private static final long serialVersionUID = 1L;

	public ProdutoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public ProdutoNaoEncontradoException(Long produtoId, Long restauranteId) {
		this(String.format(String.format("Não existe produto de código %d cadastrado para o restaurante de código %d", produtoId, restauranteId)));
	}

}
