package com.projetos.henrique.projeto2.dtos;

import java.time.LocalDate;

import com.projetos.henrique.projeto2.enumerations.CondicaoClimatica;
import com.projetos.henrique.projeto2.enumerations.Tempo;
import com.projetos.henrique.projeto2.models.Relatorio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RelatorioDto {

	@NotNull
	private LocalDate dataRelatorio;
	
	@NotBlank
	private String empresaExecutora;
	
	@NotNull
	private boolean condicao;

	@NotBlank
	private String nomeObra;
	
	@NotNull
	private CondicaoClimatica condicaoClimatica;
	
	@NotNull
	private Tempo tempo;
	
	public static RelatorioDto fromEntity(Relatorio relatorio) {

        RelatorioDto dto = new RelatorioDto();

        dto.setDataRelatorio(relatorio.getDataRelatorio());
        dto.setEmpresaExecutora(relatorio.getEmpresaExecutora());
        dto.setCondicao(relatorio.isCondicao());
        dto.setNomeObra(relatorio.getObra().getNome());
        dto.setCondicaoClimatica(relatorio.getCondicaoClimatica());
        dto.setTempo(relatorio.getTempo());

        return dto;
    }

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

	public String getNomeObra() {
		return nomeObra;
	}

	public void setNomeObra(String nomeObra) {
		this.nomeObra = nomeObra;
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
