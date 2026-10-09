package com.projetos.henrique.projeto2.dtos;

import jakarta.validation.constraints.NotBlank;

public class ContatoDto {

	@NotBlank
	private String nome;
	
	@NotBlank
	private String email;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
