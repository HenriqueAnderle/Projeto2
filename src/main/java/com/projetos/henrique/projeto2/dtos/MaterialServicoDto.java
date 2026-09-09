package com.projetos.henrique.projeto2.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.projetos.henrique.projeto2.enumerations.Unidade;
import com.projetos.henrique.projeto2.models.MaterialServico;

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
	private UUID idSolicitacao;
	
	public static List<MaterialServicoDto> fromEntity(List<MaterialServico> materiaisServicos) {
		
		List<MaterialServicoDto> dtos = new ArrayList<MaterialServicoDto>();
		
		for(MaterialServico materialServico : materiaisServicos) {
		
			MaterialServicoDto dto = new MaterialServicoDto();
			
			dto.setNomeMaterialServico(materialServico.getNomeMaterialServico());
			dto.setQuantidade(materialServico.getQuantidade());
			dto.setDescricao(materialServico.getDescricao());
			dto.setUnidade(materialServico.getUnidade());
			dto.setIdSolicitacao(materialServico.getSolicitacao().getIdSolicitacao());
			
			dtos.add(dto);
		}
		
		return dtos;
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

	public UUID getIdSolicitacao() {
		return idSolicitacao;
	}

	public void setIdSolicitacao(UUID idSolicitacao) {
		this.idSolicitacao = idSolicitacao;
	}
}
