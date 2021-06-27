package br.com.zup.apiVeiculos.form;

import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

import br.com.zup.apiVeiculos.modelo.Veiculo;
import br.com.zup.apiVeiculos.repository.VeiculoRepository;

public class AtualizaVeiculoForm {

	
	@NotNull @NotEmpty
	private String marca;
	@NotNull @NotEmpty
	private String modelo;
	@NotNull @NotEmpty
	private String ano;
	
	
	public Veiculo atualizar(Long id, VeiculoRepository veiculoRepository) {
		Veiculo veiculo = veiculoRepository.getById(id);
		veiculo.setMarca(this.marca);
		veiculo.setModelo(this.modelo);
		veiculo.setAno(this.ano);
		return veiculo;
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
