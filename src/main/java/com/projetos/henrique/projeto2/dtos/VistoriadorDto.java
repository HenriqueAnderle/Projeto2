package com.projetos.henrique.projeto2.dtos;

import com.projetos.henrique.projeto2.models.Relatorio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class VistoriadorDto {

	@NotBlank
	private String nome;
	
	@NotNull
	private Relatorio relatorio;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Relatorio getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(Relatorio relatorio) {
		this.relatorio = relatorio;
	}
}
