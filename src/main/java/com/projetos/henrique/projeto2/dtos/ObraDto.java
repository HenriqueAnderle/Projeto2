package com.projetos.henrique.projeto2.dtos;

import jakarta.validation.constraints.NotBlank;

public class ObraDto {

	@NotBlank
	private String nome;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

}
