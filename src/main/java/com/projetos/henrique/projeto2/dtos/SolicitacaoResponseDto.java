package com.projetos.henrique.projeto2.dtos;

import java.util.List;

import com.projetos.henrique.projeto2.models.MaterialServico;
import com.projetos.henrique.projeto2.models.Solicitacao;

public class SolicitacaoResponseDto {

	private Solicitacao solicitacao;
	
	private List<MaterialServico> materiaisServicos;

	public Solicitacao getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}

	public List<MaterialServico> getMateriaisServicos() {
		return materiaisServicos;
	}

	public void setMateriaisServicos(List<MaterialServico> materiaisServicos) {
		this.materiaisServicos = materiaisServicos;
	}
}
