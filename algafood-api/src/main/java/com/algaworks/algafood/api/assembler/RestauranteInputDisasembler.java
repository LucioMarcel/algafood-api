package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.input.RestauranteInput;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;

@Component
public class RestauranteInputDisasembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public Restaurante toDomainObject(RestauranteInput restauranteInput) {
		return modelMapper.map(restauranteInput, Restaurante.class);
	}
	
	public void copyToDomainObject(RestauranteInput restauranteInput, Restaurante restaurante) {
		/*
		 * Aqui instaciamos uma nova coxnia antes do mapeamento porque senão o JPA lançará 
		 * uma exceção caso queiramos alterar a referência da cozinha do resturante mudando o ID
		 * da cozinha no restauranteInput. Esta mudança de ID não se trata de mudar o ID da cozinha,
		 * mas sim da referência da cozinha no restaurante. Como a instânica de restaurante/cozinha dem estar
		 * sendo gerenciadas pelo JPA, pode ocorrer a exceção:
		 * org.hibernate.HibernateException: identifier of an instance of com.algaworks.algafood.domain.model.
		 * Cozinha was altered from 1 to 2]
	 	*/
		restaurante.setCozinha(new Cozinha());
		
		if (restaurante.getEndereco() != null) {
			restaurante.getEndereco().setCidade(new Cidade());
		}
	
		modelMapper.map(restauranteInput, restaurante);
	}
	
}
