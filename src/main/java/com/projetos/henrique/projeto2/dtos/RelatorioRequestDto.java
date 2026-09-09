package com.projetos.henrique.projeto2.dtos;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public class RelatorioRequestDto {

	@NotNull
	private RelatorioDto relatorio;
	
	private List<NaoConformidadeDto> naoConformidades;
	
	private List<VistoriadorDto> vistoriadores;

	public RelatorioDto getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(RelatorioDto relatorio) {
		this.relatorio = relatorio;
	}

	public List<NaoConformidadeDto> getNaoConformidades() {
		return naoConformidades;
	}

	public void setNaoConformidades(List<NaoConformidadeDto> naoConformidades) {
		this.naoConformidades = naoConformidades;
	}

	public List<VistoriadorDto> getVistoriadores() {
		return vistoriadores;
	}

	public void setVistoriadores(List<VistoriadorDto> vistoriadores) {
		this.vistoriadores = vistoriadores;
	}
}
