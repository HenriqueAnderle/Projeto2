package com.projetos.henrique.projeto2.models;

import java.io.Serializable;
import java.util.UUID;

import com.projetos.henrique.projeto2.enumerations.Unidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "material_servico")
public class MaterialServico implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_material_servico")
	private UUID idMaterialServico;
	
	@Column(name = "nome_material_servico")
	private String nomeMaterialServico;
	
	@Column(name = "quantidade_material_servico")
	private String quantidade;
	
	@Column(name = "descricao_material_servico")
	private String descricao;
	
	@Column(name = "unidade_material_servico")
	private Unidade unidade;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_solicitacao")
	private Solicitacao solicitacao;
	
	public MaterialServico() {
		
	}
	
	public MaterialServico(UUID idMaterialServico, String nomeMaterialServico, String quantidade, String descricao, Unidade unidade, 
			Solicitacao solicitacao) {
		
		this.idMaterialServico = idMaterialServico;
		this.nomeMaterialServico = nomeMaterialServico;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.unidade = unidade;
		this.solicitacao = solicitacao;
	}
	
	public MaterialServico(String nomeMaterialServico, String quantidade, String descricao, Unidade unidade, 
			Solicitacao solicitacao) {
		
		this.nomeMaterialServico = nomeMaterialServico;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.unidade = unidade;
		this.solicitacao = solicitacao;
	}
	
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
