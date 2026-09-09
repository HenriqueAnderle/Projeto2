package com.projetos.henrique.projeto2.dtos;

import java.util.List;

public class SolicitacaoRequestDto {

	private SolicitacaoDto solicitacaoDto;
	
	private List<MaterialServicoDto> materiaisServicosDto;

	public SolicitacaoDto getSolicitacaoDto() {
		return solicitacaoDto;
	}

	public void setSolicitacaoDto(SolicitacaoDto solicitacaoDto) {
		this.solicitacaoDto = solicitacaoDto;
	}

	public List<MaterialServicoDto> getMateriaisServicosDto() {
		return materiaisServicosDto;
	}

	public void setMateriaisServicosDto(List<MaterialServicoDto> materiaisServicosDto) {
		this.materiaisServicosDto = materiaisServicosDto;
	}
}
