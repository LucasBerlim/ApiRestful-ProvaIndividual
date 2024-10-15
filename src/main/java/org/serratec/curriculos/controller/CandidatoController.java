package org.serratec.curriculos.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.curriculos.dto.CandidatoDto;
import org.serratec.curriculos.model.Escolaridade;
import org.serratec.curriculos.model.VagaDesejada;
import org.serratec.curriculos.service.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidatos")
public class CandidatoController {
	
	@Autowired
	private CandidatoService service;
	
	@GetMapping
	public List<CandidatoDto> obterTodos() {
		return service.obterTodos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CandidatoDto> obterPorId(@PathVariable Long id) {
		Optional<CandidatoDto> dto = service.obterPorId(id);
		if (!dto.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dto.get());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CandidatoDto cadastrarCandidato(@RequestBody @Valid CandidatoDto dto) {
		return service.salvarCandidato(dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletaCandidato(@PathVariable Long id){
		if(!service.apagarCandidato(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CandidatoDto> alterarPedido(@PathVariable Long id, @RequestBody @Valid CandidatoDto dto){
		Optional<CandidatoDto> pedidoAlterado = service.alterarPedido(id, dto);
		if (!pedidoAlterado.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pedidoAlterado.get());
	}
	
	@GetMapping("/escolaridade/{escolaridade}")
	public List<CandidatoDto> obterPorEscolaridade(@PathVariable Escolaridade escolaridade) {
		return service.obterPorEscolaridade(escolaridade);
	}
	
	@GetMapping("/vaga/{nomeVaga}")
	public List<CandidatoDto> obterPorVaga(@PathVariable VagaDesejada nomeVaga) {
		return service.obterPorVagaDesejada(nomeVaga);
	}
}
