package br.com.zup.apiVeiculos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.apiVeiculos.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>  {

}
