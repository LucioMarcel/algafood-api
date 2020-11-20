package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.algaworks.algafood.core.validation.Groups;
import com.algaworks.algafood.core.validation.ValorZeroIncluiDescricao;

import lombok.Data;
import lombok.EqualsAndHashCode;

@ValorZeroIncluiDescricao(zeroField = "taxaFrete", descriptionField = "nome", descriptionmandatory="Frete Grátis")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {

	@Id
	@GeneratedValue(strategy = GenerationType .IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	//@NotNull
	//@NotBlank
	@Column(nullable = false)
	private String nome;
	
	//@DecimalMin("0")
	//@PositiveOrZero(message = "{TaxaFrete.invalida}")
	//@NotNull
	//@TaxaFrete
	//@Multiplo(numero =5)
	@Column(nullable = false)
	private BigDecimal taxaFrete;
	
	//@JsonIgnore
	//@JsonIgnoreProperties("hibernateLazyInitializer")
	//@JsonIgnoreProperties(value = "nome", allowGetters = true)
	//@Valid
	//@ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
	//@NotNull
	@ManyToOne //(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Cozinha cozinha;
	
	//@JsonIgnore
	@Embedded
	private Endereco endereco;
	
	//@JsonIgnore
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataCadastro;
	
	//@JsonIgnore
	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataAtualizacao;
	
	//@JsonIgnore
	@ManyToMany
	@JoinTable(name = "restaurante_forma_pagamento", 
			joinColumns = @JoinColumn(name = "restaurante_id"),
			inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
	private List<FormaPagamento> formasPagamento = new ArrayList<>(); 
	
	//@JsonIgnore
	@OneToMany(mappedBy = "restaurante")
	private List<Produto> produtos = new ArrayList<>();
	
}
