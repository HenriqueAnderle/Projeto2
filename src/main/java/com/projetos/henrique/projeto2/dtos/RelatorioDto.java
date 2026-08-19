package com.projetos.henrique.projeto2.dtos;

import java.time.LocalDate;

import com.projetos.henrique.projeto2.enumerations.CondicaoClimatica;
import com.projetos.henrique.projeto2.enumerations.Tempo;
import com.projetos.henrique.projeto2.models.Obra;
import com.projetos.henrique.projeto2.models.Usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RelatorioDto {

	@NotNull
	private LocalDate dataRelatorio;
	
	@NotBlank
	private String empresaExecutora;
	
	@NotNull
	private boolean condicao;

	@NotNull
	private Obra obra;
	
	@NotNull
	private Usuario usuario;
	
	@NotNull
	private CondicaoClimatica condicaoClimatica;
	
	@NotNull
	private Tempo tempo;

	public LocalDate getDataRelatorio() {
		return dataRelatorio;
	}

	public void setDataRelatorio(LocalDate dataRelatorio) {
		this.dataRelatorio = dataRelatorio;
	}

	public String getEmpresaExecutora() {
		return empresaExecutora;
	}

	public void setEmpresaExecutora(String empresaExecutora) {
		this.empresaExecutora = empresaExecutora;
	}

	public boolean isCondicao() {
		return condicao;
	}

	public void setCondicao(boolean condicao) {
		this.condicao = condicao;
	}

	public Obra getObra() {
		return obra;
	}

	public void setObra(Obra obra) {
		this.obra = obra;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public CondicaoClimatica getCondicaoClimatica() {
		return condicaoClimatica;
	}

	public void setCondicaoClimatica(CondicaoClimatica condicaoClimatica) {
		this.condicaoClimatica = condicaoClimatica;
	}

	public Tempo getTempo() {
		return tempo;
	}

	public void setTempo(Tempo tempo) {
		this.tempo = tempo;
	}
}
