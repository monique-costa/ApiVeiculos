package br.com.zup.apiVeiculos.form;

import br.com.zup.apiVeiculos.modelo.Usuario;
import br.com.zup.apiVeiculos.repository.UsuarioRepository;

public class AtualizaUsuarioForm {
	
	private String nome;
	private String email;
	
	
	public Usuario atualizar(Long id, UsuarioRepository usuarioRepository) {
		Usuario usuario = usuarioRepository.getById(id);
		usuario.setNome(this.nome);
		usuario.setEmail(this.email);
		return usuario;
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
	
	
	
}
