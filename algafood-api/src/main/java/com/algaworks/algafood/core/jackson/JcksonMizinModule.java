package com.algaworks.algafood.core.jackson;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.module.SimpleModule;

@Component
public class JcksonMizinModule extends SimpleModule {

	private static final long serialVersionUID = 1L;
	
	public JcksonMizinModule() {
		//setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
		//setMixInAnnotation(Cidade.class, CidadeMixin.class);
		//setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
		//setMixInAnnotation(Grupo.class, GrupoMixin.class);
	}
	
	
	

}
