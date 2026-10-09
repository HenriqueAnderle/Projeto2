package com.projetos.henrique.projeto2.models;

import java.io.Serializable;
import java.util.UUID;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

import com.projetos.henrique.projeto2.enumerations.RoleNome;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority, Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_role")
    private UUID idRole;
    
	@Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true, name = "nome_role")
    private RoleNome roleNome;

	
	@Override
	public @Nullable String getAuthority() {
		return this.roleNome.toString();
	}

	public UUID getIdRole() {
		return idRole;
	}


	public void setIdRole(UUID idRole) {
		this.idRole = idRole;
	}


	public RoleNome getRoleNome() {
		return roleNome;
	}


	public void setRoleNome(RoleNome roleNome) {
		this.roleNome = roleNome;
	}
}
