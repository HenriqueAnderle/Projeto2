package com.projetos.henrique.projeto2.dtos;

import com.projetos.henrique.projeto2.enumerations.Unidade;
import com.projetos.henrique.projeto2.models.Solicitacao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MaterialServicoDto {

	@NotBlank
	private String nomeMaterialServico;
	
	@NotBlank
	private String quantidade;
	
	@NotBlank
	private String descricao;
	
	@NotNull
	private Unidade unidade;
	
	@NotNull
	private Solicitacao solicitacao;

	public String getNomeMaterialServico() {
		return nomeMaterialServico;
	}

	public void setNomeMaterialServico(String nomeMaterialServico) {
		this.nomeMaterialServico = nomeMaterialServico;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public Solicitacao getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}
}
