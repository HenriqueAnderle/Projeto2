package com.projetos.henrique.projeto2.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.projetos.henrique.projeto2.models.Vistoriador;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class VistoriadorDto {

	@NotBlank
	private String nome;
	
	@NotNull
	private UUID idRelatorio;
	
	public static List<VistoriadorDto> fromEntity(List<Vistoriador> vistoriadores){
		
		List<VistoriadorDto> vistoriadoresDtos = new ArrayList<VistoriadorDto>();
		
		for(Vistoriador vistoriador : vistoriadores) {
			
			VistoriadorDto dto = new VistoriadorDto();
			
			dto.setNome(vistoriador.getNome());
			dto.setRelatorio(vistoriador.getRelatorio().getIdRelatorio());
			
			vistoriadoresDtos.add(dto);
		}
		
		return vistoriadoresDtos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public UUID getRelatorio() {
		return idRelatorio;
	}

	public void setRelatorio(UUID idRelatorio) {
		this.idRelatorio = idRelatorio;
	}
}
