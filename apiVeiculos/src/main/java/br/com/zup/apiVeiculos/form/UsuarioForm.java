package br.com.zup.apiVeiculos.form;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

import br.com.zup.apiVeiculos.modelo.Usuario;

public class UsuarioForm {
	
	@NotNull @NotEmpty
	private String nome;
	@NotNull @NotEmpty
	private String email;
	@NotNull @NotEmpty
	private String cpf;
	private LocalDate dataNascimento;
	
	
	public Usuario converter() {
		return new Usuario(nome, email, cpf, dataNascimento);
	}
	
	
	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getCpf() {
		return cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	
	
}
