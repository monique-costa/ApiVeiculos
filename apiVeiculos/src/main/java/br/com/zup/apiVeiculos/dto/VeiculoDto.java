package br.com.zup.apiVeiculos.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.zup.apiVeiculos.modelo.DiaRodizio;
import br.com.zup.apiVeiculos.modelo.Usuario;
import br.com.zup.apiVeiculos.modelo.Veiculo;

public class VeiculoDto {
	
	private Long id;
	private String marca;
	private String modelo;
	private String ano;
	private Float preco;
	private UsuarioDto proprietario;
	private DiaRodizio rodizio;
	private Boolean rodizioAtivo;
	
	
	public VeiculoDto(Veiculo veiculo) {
		this.id = veiculo.getId();
		this.marca = veiculo.getMarca();
		this.modelo = veiculo.getModelo();
		this.ano = veiculo.getAno();
		this.preco = veiculo.getPreco();
		this.proprietario = new UsuarioDto(veiculo.getProprietario());
		this.rodizio = veiculo.getRodizio();
		this.rodizioAtivo = veiculo.getRodizioAtivo();
	}
	
	public static List<VeiculoDto> converter(List<Veiculo> veiculos){
		return veiculos.stream().map(VeiculoDto::new).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
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

	public Float getPreco() {
		return preco;
	}

	public UsuarioDto getProprietario() {
		return proprietario;
	}

	public DiaRodizio getRodizio() {
		return rodizio;
	}

	public Boolean getRodizioAtivo() {
		return rodizioAtivo;
	}
	
	
	
	
}
