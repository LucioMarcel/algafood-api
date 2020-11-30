package com.algaworks.algafood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algaworks.algafood.api.model.EnderecoModel;
import com.algaworks.algafood.api.model.ItemPedidoModel;
import com.algaworks.algafood.api.model.input.ItemPedidoInput;
import com.algaworks.algafood.domain.model.Endereco;
import com.algaworks.algafood.domain.model.ItemPedido;

@Configuration
public class ModelMapperConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		
//		modelMapper.createTypeMap(Restaurante.class, RestauranteModel.class)
//			.addMapping(Restaurante::getTaxaFrete, RestauranteModel::setPrecoFrete);

		modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class)
			.addMappings(mapper -> mapper.skip(ItemPedido::setId));
		
		var enderecoToEnderecoModelTypeMap = modelMapper.createTypeMap(Endereco.class, EnderecoModel.class);

		enderecoToEnderecoModelTypeMap.<String>addMapping(
				enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
				(enderecoModeldest, value) -> enderecoModeldest.getCidade().setEstado(value));
		
		
//		var itemPedidoToItemPedidoModelTypeMap = modelMapper.createTypeMap(ItemPedido.class, ItemPedidoModel.class);
//		
//		itemPedidoToItemPedidoModelTypeMap.<Long>addMapping(
//				itemPedido -> itemPedido.getProduto().getId(),
//				(itemPedidoModel, value) -> itemPedidoModel.setProdutoId(value));
//		
//		itemPedidoToItemPedidoModelTypeMap.<String>addMapping(
//				itemPedido -> itemPedido.getProduto().getDescricao(), 
//				(itemPedidoModel, value) -> itemPedidoModel.setProdutoNome(value));
		
		return modelMapper;
	}
	

}
