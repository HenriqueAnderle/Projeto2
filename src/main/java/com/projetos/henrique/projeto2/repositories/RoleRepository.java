package com.projetos.henrique.projeto2.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetos.henrique.projeto2.enumerations.RoleNome;
import com.projetos.henrique.projeto2.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID>{
	
	Role findByRoleNome(RoleNome roleNome);
}
