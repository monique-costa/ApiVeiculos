package br.com.zup.apiVeiculos.form;

import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

import br.com.zup.apiVeiculos.modelo.Veiculo;

public class VeiculoForm {
	
	@NotNull @NotEmpty
	private String marca;
	@NotNull @NotEmpty
	private String modelo;
	@NotNull @NotEmpty
	private String ano;
	
	
	public Veiculo converter() throws Exception {
		return new Veiculo(marca, modelo, ano);
	}
	
	
	public String getMarca() {
		return marca;
	}
	public String getModelo() {
		return modelo;
	}
	public String getAno() {
		return ano;
	}
	
	
	
	
}
