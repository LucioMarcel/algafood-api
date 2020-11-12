package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioExceprion;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstado;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Estado> listar(){
		return estadoRepository.findAll();
	}
	
	@GetMapping("/{estadoId}")
	public Estado buscar(@PathVariable Long estadoId) {
		return cadastroEstado.buscarOuFalhar(estadoId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Estado adicionar(@RequestBody Estado estado) {
		try {
			return cadastroEstado.salvar(estado);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioExceprion(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{estadoId}")
	public Estado atualizar(@PathVariable Long estadoId, @RequestBody Estado estado) {
		try {
			Estado estadoAtual = cadastroEstado.buscarOuFalhar(estadoId);
			BeanUtils.copyProperties(estado, estadoAtual, "id");
			return cadastroEstado.salvar(estadoAtual); 
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioExceprion(e.getMessage(), e);
		}
	}
	
	@DeleteMapping("/{estadoId")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir (@PathVariable Long estadoId) {
		cadastroEstado.excluir(estadoId);
	}
}