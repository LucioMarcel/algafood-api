package com.algaworks.algafood.domain.exception;

public class GruponaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public GruponaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public GruponaoEncontradoException(Long grupoId) {
		this(String.format("Não existe cadastro de grupo com o código %d.", grupoId));
	}
	
	

}
