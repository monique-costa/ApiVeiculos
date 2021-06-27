package br.com.zup.apiVeiculos.modelo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.zup.apiVeiculos.apiFipe.BuscaPreco;
import br.com.zup.apiVeiculos.apiFipe.ObjetoDetalhado;

@Entity
public class Veiculo {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String marca;
	private String modelo;
	private String ano;
	private Float preco;
	@ManyToOne
	private Usuario proprietario;
	@Enumerated(EnumType.STRING)
	private DiaRodizio rodizio;
	private Boolean rodizioAtivo;
	
	public Veiculo() {
		
	}
	
	public Veiculo(String marca, String modelo, String ano) throws Exception {
		this.marca = marca;
		this.modelo = modelo;
		this.ano = ano;
		
		BuscaPreco busca = new BuscaPreco();
		ObjetoDetalhado detalhes = busca.definirPreco(marca, modelo, ano.toString());
		this.preco = Float.parseFloat(detalhes.getValor().substring(3).replace(",",""));
		definirRodizio(detalhes.getAnomodelo());
		
	}
	
	public void definirRodizio(String ano) {
		int ultimoDigito = Integer.parseInt(ano) % 10;
		
		if (ultimoDigito == 0 || ultimoDigito == 1) {
			this.rodizio = DiaRodizio.SEGUNDA;
		} else if (ultimoDigito == 2 || ultimoDigito == 3) {
			this.rodizio = DiaRodizio.TERÇA;
		} else if (ultimoDigito == 4 || ultimoDigito == 5) {
			this.rodizio = DiaRodizio.QUARTA;
		} else if (ultimoDigito == 6 || ultimoDigito == 7) {
			this.rodizio = DiaRodizio.QUINTA;
		} else if (ultimoDigito == 8 || ultimoDigito == 9) {
			this.rodizio = DiaRodizio.SEXTA;
		}
		
		this.rodizioAtivo = getStatusRodizio(ultimoDigito);
	}
	
	public boolean getStatusRodizio(Integer ultimoDigito) {
		LocalDate hoje = LocalDate.now();
		int diaDaSemana = hoje.getDayOfWeek().getValue();
		rodizioAtivo = false;
		
		if (ultimoDigito == 0 || ultimoDigito == 1) {
			rodizioAtivo = (diaDaSemana == 1);
		} else if (ultimoDigito == 2 || ultimoDigito == 3) {
			rodizioAtivo = (diaDaSemana == 2);
		} else if (ultimoDigito == 4 || ultimoDigito == 5) {
			rodizioAtivo = (diaDaSemana == 3);
		} else if (ultimoDigito == 6 || ultimoDigito == 7) {
			rodizioAtivo = (diaDaSemana == 4);
		} else if (ultimoDigito == 8 || ultimoDigito == 9) {
			rodizioAtivo = (diaDaSemana == 5);
		}
		
		return rodizioAtivo;
	}
	
	public Long getId() {
		return id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public Float getPreco() {
		return preco;
	}

	public void setPreco(Float preco) {
		this.preco = preco;
	}

	public Usuario getProprietario() {
		return proprietario;
	}
	
	
	public void setProprietario(Usuario proprietario) {
		this.proprietario = proprietario;
	}

	public DiaRodizio getRodizio() {
		return rodizio;
	}

	public Boolean getRodizioAtivo() {
		return rodizioAtivo;
	}
	
	
	
}

