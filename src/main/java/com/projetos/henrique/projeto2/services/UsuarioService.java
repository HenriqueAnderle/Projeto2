package com.projetos.henrique.projeto2.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projetos.henrique.projeto2.models.Usuario;
import com.projetos.henrique.projeto2.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService implements UserDetailsService{

	final UsuarioRepository usuarioRepository;
	
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	public Usuario inserirUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public void deletarUsuario(Usuario usuario) {
		usuarioRepository.delete(usuario);
	}
	
	public Optional<Usuario> findById(UUID idUsuario) {
		return usuarioRepository.findById(idUsuario);
	}
	
	public Optional<Usuario> findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}
	
	public Page<Usuario> findAll(Pageable pageable){
		return usuarioRepository.findAll(pageable);
	}
	
	public boolean existsByEmail(String email) {
		return usuarioRepository.existsByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
		
		return User.builder().username(usuario.get().getUsername()).password(usuario.get().getPassword()).authorities(usuario.get().getRoles()).build();
	}
	
}
