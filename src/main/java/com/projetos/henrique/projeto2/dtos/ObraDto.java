package com.projetos.henrique.projeto2.dtos;

import com.projetos.henrique.projeto2.models.Usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ObraDto {

	@NotBlank
	private String nome;
	
	@NotNull
	private Usuario usuario;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
