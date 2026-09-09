package com.projetos.henrique.projeto2.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.projetos.henrique.projeto2.enumerations.EtapaDaObra;
import com.projetos.henrique.projeto2.enumerations.Solicitante;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "solicitacao")
public class Solicitacao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_solicitacao")
	private UUID idSolicitacao;
	
	@Column(name = "observacao_solicitacao")
	private String observacao;
	
	@Column(name = "imagem_solicitacao")
	private String imagem;
	
	@Column(name = "logo_solicitacao")
	private boolean logo;
	
	@Column(name = "data_solicitacao")
	private LocalDate dataSolicitacao;
	
	@Column(name = "previsao_chegada_solicitacao")
	private LocalDateTime previsaoChegada;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_obra")
	private Obra obra;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@Column(name = "solicitante_solicitacao")
	private Solicitante solicitante;
	
	@Column(name = "etapa_da_obra_solicitacao")
	private EtapaDaObra etapaDaObra;
	
	public Solicitacao() {
		
	}
	
	public Solicitacao(UUID idSolicitacao, String observacao, String imagem, boolean logo, LocalDate dataSolicitacao, LocalDateTime previsaoChegada,
			Obra obra, Usuario usuario, Solicitante solicitante, EtapaDaObra etapaDaObra) {
		
		this.idSolicitacao = idSolicitacao;
		this.observacao = observacao;
		this.imagem = imagem;
		this.logo = logo;
		this.dataSolicitacao = dataSolicitacao;
		this.previsaoChegada = previsaoChegada;
		this.obra = obra;
		this.usuario = usuario;
		this.solicitante = solicitante;
		this.etapaDaObra = etapaDaObra;
	}
	
	public Solicitacao(String observacao, String imagem, boolean logo, LocalDate dataSolicitacao, LocalDateTime previsaoChegada,
			Obra obra, Usuario usuario, Solicitante solicitante, EtapaDaObra etapaDaObra) {
		
		this.observacao = observacao;
		this.imagem = imagem;
		this.logo = logo;
		this.dataSolicitacao = dataSolicitacao;
		this.previsaoChegada = previsaoChegada;
		this.obra = obra;
		this.usuario = usuario;
		this.solicitante = solicitante;
		this.etapaDaObra = etapaDaObra;
	}
	
	public UUID getIdSolicitacao() {
		return idSolicitacao;
	}
	public void setIdSolicitacao(UUID idSolicitacao) {
		this.idSolicitacao = idSolicitacao;
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
	public boolean getLogo() {
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
