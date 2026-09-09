package com.projetos.henrique.projeto2.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.projetos.henrique.projeto2.enumerations.EtapaDaObra;
import com.projetos.henrique.projeto2.enumerations.Solicitante;
import com.projetos.henrique.projeto2.models.Solicitacao;

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
	private String nomeObra;
	
	@NotNull
	private UUID idUsuario;
	
	@NotNull
	private Solicitante solicitante;
	
	@NotNull
	private EtapaDaObra etapaDaObra;
	
	public static SolicitacaoDto fromEntity(Solicitacao solicitacao) {
		
		SolicitacaoDto dto = new SolicitacaoDto();
		
		dto.setObservacao(solicitacao.getObservacao());
		dto.setLogo(solicitacao.getLogo());
		dto.setDataSolicitacao(solicitacao.getDataSolicitacao());
		dto.setPrevisaoChegada(solicitacao.getPrevisaoChegada());
		dto.setNomeObra(solicitacao.getObra().getNome());
		dto.setIdUsuario(solicitacao.getUsuario().getIdUsuario());
		dto.setSolicitante(solicitacao.getSolicitante());
		dto.setEtapaDaObra(solicitacao.getEtapaDaObra());
		
		return dto;
	}

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

	public String getNomeObra() {
		return nomeObra;
	}

	public void setNomeObra(String nomeObra) {
		this.nomeObra = nomeObra;
	}

	public UUID getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(UUID idUsuario) {
		this.idUsuario = idUsuario;
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
