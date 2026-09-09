package com.projetos.henrique.projeto2.dtos;

import java.util.List;

import com.projetos.henrique.projeto2.models.NaoConformidade;
import com.projetos.henrique.projeto2.models.Relatorio;
import com.projetos.henrique.projeto2.models.Vistoriador;

public class RelatorioResponseDto {

	private Relatorio relatorio;
	
	private List<NaoConformidade> naoConformidades;
	
	private List<Vistoriador> vistoriadores;

	public Relatorio getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(Relatorio relatorio) {
		this.relatorio = relatorio;
	}

	public List<NaoConformidade> getNaoConformidades() {
		return naoConformidades;
	}

	public void setNaoConformidades(List<NaoConformidade> naoConformidades) {
		this.naoConformidades = naoConformidades;
	}

	public List<Vistoriador> getVistoriadores() {
		return vistoriadores;
	}

	public void setVistoriadores(List<Vistoriador> vistoriadores) {
		this.vistoriadores = vistoriadores;
	}
}
