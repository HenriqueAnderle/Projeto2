package com.projetos.henrique.projeto2.models;

import java.io.Serializable;
import java.util.UUID;

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
@Table(name = "vistoriador")
public class Vistoriador implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_vistoriador")
	private UUID idVistoriador;
	
	@Column(name = "nome_vistoriador")
	private String nome;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_relatorio")
	private Relatorio relatorio;
	
	public Vistoriador() {
		
	}
	
	public Vistoriador(UUID idVistoriador, String nome, Relatorio relatorio) {
		this.idVistoriador = idVistoriador;
		this.nome = nome;
		this.relatorio = relatorio;
	}
	
	public Vistoriador(String nome, Relatorio relatorio) {
		this.nome = nome;
		this.relatorio = relatorio;
	}
	
	public UUID getIdVistoriador() {
		return idVistoriador;
	}
	public void setIdVistoriador(UUID idVistoriador) {
		this.idVistoriador = idVistoriador;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Relatorio getRelatorio() {
		return relatorio;
	}
	public void setRelatorio(Relatorio relatorio) {
		this.relatorio = relatorio;
	}
}
