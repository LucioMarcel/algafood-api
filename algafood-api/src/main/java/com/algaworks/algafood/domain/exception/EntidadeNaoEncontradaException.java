package com.algaworks.algafood.domain.exception;

public abstract class EntidadeNaoEncontradaException extends NegocioExceprion {

	private static final long serialVersionUID = 1L;
	
	public EntidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
}