package org.serratec.curriculos.service;

import java.util.List;
import java.util.Optional;

import org.serratec.curriculos.dto.CandidatoDto;
import org.serratec.curriculos.model.Candidato;
import org.serratec.curriculos.model.Escolaridade;
import org.serratec.curriculos.model.VagaDesejada;
import org.serratec.curriculos.repository.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidatoService {
	
	@Autowired
	private CandidatoRepository repository;
	
	public List<CandidatoDto> obterTodos(){
		return repository.findAll().stream().map(c -> CandidatoDto.toDto(c)).toList();
	}
	
	public Optional<CandidatoDto> obterPorId(Long id){
		if(!repository.existsById(id)) {
			return Optional.empty();
		}
		return Optional.of(CandidatoDto.toDto(repository.findById(id).get()));
	}
	
	public CandidatoDto salvarCandidato(CandidatoDto dto) {
		Candidato candidatoEntity = repository.save(dto.toEntity());
		return CandidatoDto.toDto(candidatoEntity);
	}
	
	public boolean apagarCandidato(Long id) {
		if(!repository.existsById(id)) {
			return false;
		}
		repository.deleteById(id);
		return true;
	}
	
	public Optional<CandidatoDto> alterarPedido(Long id, CandidatoDto dto){
		if(!repository.existsById(id)) {
			return Optional.empty();
		}
		Candidato candidatoEntity = dto.toEntity();
		candidatoEntity.setId(id);
		repository.save(candidatoEntity);
		return Optional.of(CandidatoDto.toDto(candidatoEntity));
	}
	
	public List<CandidatoDto> obterPorVagaDesejada(VagaDesejada nomeVaga) {
		List<Candidato> candidatos = repository.findByVagaDesejada(nomeVaga);
		return candidatos.stream().map(c -> CandidatoDto.toDto(c)).toList();
	}
	
	public List<CandidatoDto> obterPorEscolaridade(Escolaridade escolaridade) {
		List<Candidato> candidatos = repository.findByEscolaridade(escolaridade);
		return candidatos.stream().map(c -> CandidatoDto.toDto(c)).toList();
	}
	

}
