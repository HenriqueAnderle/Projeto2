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
@Table(name = "contato")
public class Contato implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_contato")
	private UUID idContato;
	
	@Column(name = "nome_contato")
	private String nome;
	
	@Column(name = "email_contato")
	private String email;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	public Contato() {
		
	}
	
	public Contato(UUID idContato, String nome, String email, Usuario usuario) {
		this.idContato = idContato;
		this.nome = nome;
		this.email = email;
		this.usuario = usuario;
	}
	
	public Contato(String nome, String email, Usuario usuario) {
		this.nome = nome;
		this.email = email;
		this.usuario = usuario;
	}
	
	public UUID getIdContato() {
		return idContato;
	}

	public void setIdContato(UUID idContato) {
		this.idContato = idContato;
	}

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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
