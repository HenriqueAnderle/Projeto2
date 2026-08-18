package com.projetos.henrique.projeto2.models;

import java.time.LocalDate;
import java.util.UUID;

import com.projetos.henrique.projeto2.enumerations.CondicaoClimatica;
import com.projetos.henrique.projeto2.enumerations.Tempo;

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
@Table(name = "relatorio")
public class Relatorio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_relatorio")
	private UUID idRelatorio;
	
	@Column(name = "data_relatorio")
	private LocalDate dataRelatorio;
	
	@Column(name = "empresa_executora_relatorio")
	private String empresaExecutora;
	
	@Column(name = "condicao_relatorio")
	private boolean condicao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_obra")
	private Obra obra;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@Column(name = "condicao_climatica_relatorio")
	private CondicaoClimatica condicaoClimatica;
	
	@Column(name = "tempo_relatorio")
	private Tempo tempo;
	
	public Relatorio() {
		
	}
	
	public Relatorio(UUID idRelatorio, LocalDate dataRelatorio, String empresaExecutora, boolean condicao, Obra obra, Usuario usuario,
			CondicaoClimatica condicaoClimatica, Tempo tempo) {
		
		this.idRelatorio = idRelatorio;
		this.dataRelatorio = dataRelatorio;
		this.empresaExecutora = empresaExecutora;
		this.condicao = condicao;
		this.obra = obra;
		this.usuario = usuario;
		this.condicaoClimatica = condicaoClimatica;
		this.tempo = tempo;
	}
	
	public Relatorio(LocalDate dataRelatorio, String empresaExecutora, boolean condicao, Obra obra, Usuario usuario,
			CondicaoClimatica condicaoClimatica, Tempo tempo) {
		
		this.dataRelatorio = dataRelatorio;
		this.empresaExecutora = empresaExecutora;
		this.condicao = condicao;
		this.obra = obra;
		this.usuario = usuario;
		this.condicaoClimatica = condicaoClimatica;
		this.tempo = tempo;
	}
	
	public UUID getIdRelatorio() {
		return idRelatorio;
	}
	public void setIdRelatorio(UUID idRelatorio) {
		this.idRelatorio = idRelatorio;
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
