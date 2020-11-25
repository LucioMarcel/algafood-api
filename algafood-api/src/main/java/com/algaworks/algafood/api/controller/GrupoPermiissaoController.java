package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.PermissaoModelAssembler;
import com.algaworks.algafood.api.model.PermissaoModel;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.service.CadastroGrupoService;

@RestController
@RequestMapping("/grupos/{grupoId}/permissoes")
public class GrupoPermiissaoController {
	
	@Autowired
	private CadastroGrupoService cadastroGrupoService;
	
	@Autowired
	private PermissaoModelAssembler permissaoModelAssembler;
	
	
	@GetMapping
	public List<PermissaoModel> listar(@PathVariable Long grupoId) {
		Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId);
				
		return permissaoModelAssembler.toCollectionModel(grupo.getPermissoes());
	}
	
	@PutMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associarPermissao(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		cadastroGrupoService.associarPermissao(grupoId, permissaoId);
	}
	
	@DeleteMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociarPermissao(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		cadastroGrupoService.desassociarPermissao(grupoId, permissaoId);
	}
	
}
