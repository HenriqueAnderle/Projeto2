package com.projetos.henrique.projeto2.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetos.henrique.projeto2.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID>{

	boolean existsByEmail(String email);
	
}
