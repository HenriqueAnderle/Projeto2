package com.projetos.henrique.projeto2.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.projetos.henrique.projeto2.enumerations.EtapaDaObra;
import com.projetos.henrique.projeto2.enumerations.Solicitante;
import com.projetos.henrique.projeto2.models.Obra;
import com.projetos.henrique.projeto2.models.Usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SolicitacaoDto {

	@NotBlank
	private String observacao;
	
	@NotNull
	private String imagem;
	
	@NotNull
	private boolean logo;
	
	@NotNull
	private LocalDate dataSolicitacao;
	
	@NotNull
	private LocalDateTime previsaoChegada;
	
	@NotNull
	private Obra obra;
	
	@NotNull
	private Usuario usuario;
	
	@NotNull
	private Solicitante solicitante;
	
	@NotNull
	private EtapaDaObra etapaDaObra;

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public boolean isLogo() {
		return logo;
	}

	public void setLogo(boolean logo) {
		this.logo = logo;
	}

	public LocalDate getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(LocalDate dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public LocalDateTime getPrevisaoChegada() {
		return previsaoChegada;
	}

	public void setPrevisaoChegada(LocalDateTime previsaoChegada) {
		this.previsaoChegada = previsaoChegada;
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

	public Solicitante getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Solicitante solicitante) {
		this.solicitante = solicitante;
	}

	public EtapaDaObra getEtapaDaObra() {
		return etapaDaObra;
	}

	public void setEtapaDaObra(EtapaDaObra etapaDaObra) {
		this.etapaDaObra = etapaDaObra;
	}
}
