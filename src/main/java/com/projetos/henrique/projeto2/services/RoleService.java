package com.projetos.henrique.projeto2.services;

import org.springframework.stereotype.Service;

import com.projetos.henrique.projeto2.enumerations.RoleNome;
import com.projetos.henrique.projeto2.models.Role;
import com.projetos.henrique.projeto2.repositories.RoleRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RoleService {

	final RoleRepository roleRepository;
	
	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	public Role findByRoleNome(RoleNome roleNome) {
		return roleRepository.findByRoleNome(roleNome);
	}
}
