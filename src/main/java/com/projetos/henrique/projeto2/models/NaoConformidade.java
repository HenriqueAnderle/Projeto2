package com.projetos.henrique.projeto2.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.projetos.henrique.projeto2.enumerations.EtapaDaObra;
import com.projetos.henrique.projeto2.enumerations.Pavimento;
import com.projetos.henrique.projeto2.enumerations.TipoDeNaoConformidade;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "nao_conformidade")
public class NaoConformidade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_nao_conformidade")
	private UUID idNaoConformidade;
	
	@ElementCollection
	@CollectionTable(
	    name = "nao_conformidade_imagens",
	    joinColumns = @JoinColumn(name = "id_nao_conformidade")
	)
	@Column(name = "imagem", columnDefinition = "bytea")
	private List<byte[]> imagens = new ArrayList<>();
	
	@Column(name = "observacao_nao_conformidade")
	private String observacao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_relatorio")
	private Relatorio relatorio;
	
	@Column(name = "pavimento_nao_conformidade")
	private Pavimento pavimento;
	
	@Column(name = "etapa_da_obra_nao_conformidade")
	private EtapaDaObra etapaDaObra;
	
	@Column(name = "tipo_nao_conformidade")
	private TipoDeNaoConformidade tipoDeNaoConformidade;
	
	public NaoConformidade() {
		
	}
	
	public NaoConformidade(UUID idNaoConformidade, List<byte[]> imagens, String observacao, Relatorio relatorio, Pavimento pavimento,
			EtapaDaObra etapaDaObra, TipoDeNaoConformidade tipoDeNaoConformidade) {
		
		this.idNaoConformidade = idNaoConformidade;
		this.imagens = imagens;
		this.observacao = observacao;
		this.relatorio = relatorio;
		this.pavimento = pavimento;
		this.etapaDaObra = etapaDaObra;
		this.tipoDeNaoConformidade = tipoDeNaoConformidade;
	}
	
	public NaoConformidade(List<byte[]> imagens, String observacao, Relatorio relatorio, Pavimento pavimento,
			EtapaDaObra etapaDaObra, TipoDeNaoConformidade tipoDeNaoConformidade) {
		
		this.imagens = imagens;
		this.observacao = observacao;
		this.relatorio = relatorio;
		this.pavimento = pavimento;
		this.etapaDaObra = etapaDaObra;
		this.tipoDeNaoConformidade = tipoDeNaoConformidade;
	}
	
	public NaoConformidade(String observacao, Relatorio relatorio, Pavimento pavimento,
			EtapaDaObra etapaDaObra, TipoDeNaoConformidade tipoDeNaoConformidade) {
		
		this.observacao = observacao;
		this.relatorio = relatorio;
		this.pavimento = pavimento;
		this.etapaDaObra = etapaDaObra;
		this.tipoDeNaoConformidade = tipoDeNaoConformidade;
	}
	
	public UUID getIdNaoConformidade() {
		return idNaoConformidade;
	}
	public void setIdNaoConformidade(UUID idNaoConformidade) {
		this.idNaoConformidade = idNaoConformidade;
	}
	public List<byte[]> getImagens() {
		return imagens;
	}
	public void setImagens(List<byte[]> imagens) {
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
