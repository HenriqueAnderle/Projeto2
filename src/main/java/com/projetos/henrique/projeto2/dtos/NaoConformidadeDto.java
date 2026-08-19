package com.projetos.henrique.projeto2.dtos;

import java.util.ArrayList;
import java.util.List;

import com.projetos.henrique.projeto2.enumerations.EtapaDaObra;
import com.projetos.henrique.projeto2.enumerations.Pavimento;
import com.projetos.henrique.projeto2.enumerations.TipoDeNaoConformidade;
import com.projetos.henrique.projeto2.models.Relatorio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class NaoConformidadeDto {
	
	private List<String> imagens = new ArrayList<>();
	
	@NotBlank
	private String observacao;
	
	@NotNull
	private Relatorio relatorio;
	
	@NotNull
	private Pavimento pavimento;
	
	@NotNull
	private EtapaDaObra etapaDaObra;
	
	@NotNull
	private TipoDeNaoConformidade tipoDeNaoConformidade;

	public List<String> getImagens() {
		return imagens;
	}

	public void setImagens(List<String> imagens) {
		this.imagens = imagens;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Relatorio getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(Relatorio relatorio) {
		this.relatorio = relatorio;
	}

	public Pavimento getPavimento() {
		return pavimento;
	}

	public void setPavimento(Pavimento pavimento) {
		this.pavimento = pavimento;
	}

	public EtapaDaObra getEtapaDaObra() {
		return etapaDaObra;
	}

	public void setEtapaDaObra(EtapaDaObra etapaDaObra) {
		this.etapaDaObra = etapaDaObra;
	}

	public TipoDeNaoConformidade getTipoDeNaoConformidade() {
		return tipoDeNaoConformidade;
	}

	public void setTipoDeNaoConformidade(TipoDeNaoConformidade tipoDeNaoConformidade) {
		this.tipoDeNaoConformidade = tipoDeNaoConformidade;
	}
}
