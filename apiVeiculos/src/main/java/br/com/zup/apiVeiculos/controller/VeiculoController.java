package br.com.zup.apiVeiculos.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.apiVeiculos.dto.VeiculoDto;
import br.com.zup.apiVeiculos.form.AtualizaVeiculoForm;
import br.com.zup.apiVeiculos.form.VeiculoForm;
import br.com.zup.apiVeiculos.modelo.Usuario;
import br.com.zup.apiVeiculos.modelo.Veiculo;
import br.com.zup.apiVeiculos.repository.UsuarioRepository;
import br.com.zup.apiVeiculos.repository.VeiculoRepository;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
	
	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@GetMapping
	public List<VeiculoDto> listarTodosVeiculos(){
		List<Veiculo> veiculos = veiculoRepository.findAll();
		return VeiculoDto.converter(veiculos);
	}
	
	
	@PostMapping
	@Transactional
	@RequestMapping("/usuario/{id}")
	public ResponseEntity<VeiculoDto> cadastrarVeiculo(@PathVariable Long id, @RequestBody @Valid VeiculoForm veiculoForm, UriComponentsBuilder uriBuilder) throws Exception{
		Veiculo veiculo = veiculoForm.converter();
		Usuario usuario = usuarioRepository.findById(id).get();
		veiculo.setProprietario(usuario);
		usuario.getVeiculos().add(veiculo);
		veiculoRepository.save(veiculo);
		
		URI uri = uriBuilder.path("/veiculos/{id}").buildAndExpand(veiculo.getId()).toUri();
		return ResponseEntity.created(uri).body(new VeiculoDto(veiculo));
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<VeiculoDto> filtrarVeiculo(@PathVariable Long id) {
		Optional<Veiculo> veiculo = veiculoRepository.findById(id);
		if (veiculo.isPresent()) {
			return ResponseEntity.ok(new VeiculoDto(veiculo.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<VeiculoDto> atualizarVeiculo (@PathVariable Long id, @RequestBody @Valid AtualizaVeiculoForm form){
		Optional<Veiculo> optional = veiculoRepository.findById(id);
		if (optional.isPresent()) {
			Veiculo veiculo = form.atualizar(id, veiculoRepository);
			return ResponseEntity.ok(new VeiculoDto(veiculo));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> removerVeiculo(@PathVariable Long id){
		Optional<Veiculo> optional = veiculoRepository.findById(id);
		if (optional.isPresent()) {
			veiculoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	
	}
	
}
