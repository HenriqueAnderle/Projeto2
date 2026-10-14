package com.projetos.henrique.projeto2.dtos;

import java.util.ArrayList;
import java.util.List;

import com.projetos.henrique.projeto2.models.Vistoriador;

import jakarta.validation.constraints.NotBlank;

public class VistoriadorDto {

	@NotBlank
	private String nome;
	
	public static List<VistoriadorDto> fromEntity(List<Vistoriador> vistoriadores){
		
		List<VistoriadorDto> vistoriadoresDtos = new ArrayList<VistoriadorDto>();
		
		for(Vistoriador vistoriador : vistoriadores) {
			
			VistoriadorDto dto = new VistoriadorDto();
			
			dto.setNome(vistoriador.getNome());
			
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
}
