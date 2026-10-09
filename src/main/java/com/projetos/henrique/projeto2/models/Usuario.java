package com.projetos.henrique.projeto2.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable, UserDetails {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_usuario")
	private UUID idUsuario;
	
	@Column(name = "nome_usuario", nullable = false)
	private String nome;
	
	@Column(name = "email_usuario", nullable = false)
	private String email;
	
	@Column(name = "senha_usuario", nullable = false)
	private String senha;
	
	@Column(name = "contador_relatorio_usuario")
	private Long contadorRelatorio = (long) 0;
	
	@Column(name = "contador_solicitacao_eng_usuario")
	private Long contadorSolicitacaoEng = (long) 0;
	
	@Column(name = "contador_solicitacao_man_usuario")
	private Long contadorSolicitacaoMan = (long) 0;
	
	@ManyToMany
	@JoinTable(name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_role"))
	private List<Role> roles;
	
	public Usuario() {
		
	}
	
	public Usuario(UUID idUsuario, String nome, String email, String senha, 
			Long contadorRelatorio, Long contadorSolicitacaoEng, Long contadorSolicitacaoMan) {
		
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.contadorRelatorio = contadorRelatorio;
		this.contadorSolicitacaoEng = contadorSolicitacaoEng;
		this.contadorSolicitacaoMan = contadorSolicitacaoMan;
	}
	
	public Usuario(UUID idUsuario, String nome, String email, String senha) {
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}
	
	public Usuario(String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}
	
	public UUID getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(UUID idUsuario) {
		this.idUsuario = idUsuario;
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
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public long getContadorRelatorio() {
		return contadorRelatorio;
	}
	public void setContadorRelatorio(Long contadorRelatorio) {
		this.contadorRelatorio = contadorRelatorio;
	}
	public Long getContadorSolicitacaoEng() {
		return contadorSolicitacaoEng;
	}
	public void setContadorSolicitacaoEng(Long contadorSolicitacaoEng) {
		this.contadorSolicitacaoEng = contadorSolicitacaoEng;
	}
	public Long getContadorSolicitacaoMan() {
		return contadorSolicitacaoMan;
	}
	public void setContadorSolicitacaoMan(Long contadorSolicitacaoMan) {
		this.contadorSolicitacaoMan = contadorSolicitacaoMan;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public long proximoRelatorio(Usuario usuario) {
		contadorRelatorio = usuario.getContadorRelatorio();
		return contadorRelatorio++;
	}
	
	public long proximaSolicitacaoEng(Usuario usuario) {
		contadorSolicitacaoEng = usuario.getContadorSolicitacaoEng();
		return contadorSolicitacaoEng++;
	}
	
	public long proximaSolicitacaoMan(Usuario usuario) {
		contadorSolicitacaoMan = usuario.getContadorSolicitacaoMan();
		return contadorSolicitacaoMan++;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public @Nullable String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}
	
	@Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
