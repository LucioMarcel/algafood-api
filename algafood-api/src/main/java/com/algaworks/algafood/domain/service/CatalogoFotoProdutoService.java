package com.algaworks.algafood.domain.service;

import java.io.InputStream;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.FotoNaoEncontradaException;
import com.algaworks.algafood.domain.model.FotoProduto;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.ProdutoRepository;
import com.algaworks.algafood.domain.service.FotoStorageService.NovaFoto;

@Service
public class CatalogoFotoProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;

	@Autowired
	private CadastroProdutoService cadastroProdutoService;
	
	@Autowired
	private FotoStorageService fotoStorageService;
	
	@Transactional
	public FotoProduto salvar(FotoProduto fotoProduto, InputStream dadosArquivo) {
		Long restauranteId = fotoProduto.getRestauranteId();
		Long produtoId = fotoProduto.getProduto().getId();
		String novoNomeArquivo = fotoStorageService.gerarNomeArquivo(fotoProduto.getNomeArquivo());
		String nomeArquivoExistente = null;
			
		Optional<FotoProduto> fotoExistente = produtoRepository.findFotoById(restauranteId,produtoId);
		
		if (fotoExistente.isPresent()) {
			nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
			produtoRepository.delete(fotoExistente.get());
		}
		
		fotoProduto.setNomeArquivo(novoNomeArquivo);
		fotoProduto = produtoRepository.save(fotoProduto);
		produtoRepository.flush();
		
		NovaFoto novaFoto = NovaFoto.builder()
				.nomeArquivo(fotoProduto.getNomeArquivo())
				.contentType(fotoProduto.getContentType())
				.inputStream(dadosArquivo)
				.build();
		
		fotoStorageService.substituir(nomeArquivoExistente, novaFoto);

		return fotoProduto;
	}
	
	public FotoProduto buscarOuFalhar(Long restauranteId, Long produtoId) {
		return produtoRepository.findFotoById(restauranteId, produtoId)
				.orElseThrow(() -> new FotoNaoEncontradaException(restauranteId, produtoId));
	}
	
	@Transactional
	public void excluir(Long restauranteId, Long produtoId) {
		Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
		Produto produto = cadastroProdutoService.buscarOuFalhar(restauranteId, produtoId);
		FotoProduto foto = buscarOuFalhar(restauranteId, produtoId);
		
		try {
			produtoRepository.delete(foto);
			produtoRepository.flush();
			fotoStorageService.remover(foto.getNomeArquivo());
			
		} catch(EmptyResultDataAccessException e) {
			throw new FotoNaoEncontradaException(restauranteId, produtoId);
			
		}
		
	}

}
