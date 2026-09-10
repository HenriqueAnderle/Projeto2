package com.projetos.henrique.projeto2.dtos;

import java.util.UUID;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ContatoDto {

	@NotBlank
	private String nome;
	
	@NotBlank
	private String email;
	
	@NotNull
	private UUID idUsuario;

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

	public UUID getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(UUID idUsuario) {
		this.idUsuario = idUsuario;
	}
}
