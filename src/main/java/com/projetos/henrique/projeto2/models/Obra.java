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
@Table(name = "obra")
public class Obra implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_obra")
	private UUID idObra;
	
	@Column(name = "nome_obra")
	private String nome;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	public Obra() {
		
	}
	
	public Obra(UUID idObra, String nome, Usuario usuario) {
		this.idObra = idObra;
		this.nome = nome;
		this.usuario = usuario;
	}
	
	public Obra(String nome, Usuario usuario) {
		this.nome = nome;
		this.usuario = usuario;
	}
	
	public UUID getIdObra() {
		return idObra;
	}
	public void setIdObra(UUID idObra) {
		this.idObra = idObra;
	}
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
