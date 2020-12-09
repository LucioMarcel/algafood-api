package com.algaworks.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.FotoProdutoModel;
import com.algaworks.algafood.domain.model.FotoProduto;

@Component
public class FotoProdutoModelAssembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public FotoProdutoModel toModel(FotoProduto fotoProduto) {
		return modelMapper.map(fotoProduto, FotoProdutoModel.class);
	}
	
	public List<FotoProdutoModel> toCollectionModel(List<FotoProduto> fotos) {
		return fotos.stream()
				.map(foto -> toModel(foto))
				.collect(Collectors.toList());
	}
	
	
	

}
