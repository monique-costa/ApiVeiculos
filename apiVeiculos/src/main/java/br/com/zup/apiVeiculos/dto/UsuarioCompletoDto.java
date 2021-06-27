package br.com.zup.apiVeiculos.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.zup.apiVeiculos.modelo.Usuario;
import br.com.zup.apiVeiculos.modelo.Veiculo;

public class UsuarioCompletoDto {

	
	private Long id;
	private String nome;
	private String email;
	private String cpf;
	private LocalDate dataNascimento;
	private List<VeiculoSimplesDto> veiculos;
	
	
	public UsuarioCompletoDto(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.cpf = usuario.getCpf();
		this.dataNascimento = usuario.getDataNascimento();
		this.veiculos = new ArrayList<>();
		this.veiculos.addAll(usuario.getVeiculos().stream().map(VeiculoSimplesDto::new).collect(Collectors.toList()));
	}
	
	
	public static List<UsuarioCompletoDto> converter(List<Usuario> usuarios){
		return usuarios.stream().map(UsuarioCompletoDto::new).collect(Collectors.toList());
	}


	public Long getId() {
		return id;
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


	public List<VeiculoSimplesDto> getVeiculos() {
		return veiculos;
	}
	
	
	
}
