package br.com.zup.apiVeiculos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.apiVeiculos.modelo.Veiculo;

public interface VeiculoRepository extends JpaRepository <Veiculo, Long> {

}
