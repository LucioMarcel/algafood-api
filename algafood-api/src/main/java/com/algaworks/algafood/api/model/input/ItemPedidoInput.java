package com.algaworks.algafood.api.model.input;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoInput {
	
	@NotNull
	private Long produtoId;

	@NotNull
	@Min(value = 1)
	private BigDecimal quantidade;
	
	private String observacao;
	
}
