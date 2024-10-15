package org.serratec.curriculos.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "candidatos")
public class Candidato {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nome;
	@Past
	private LocalDate dataNascimento;
	@NotBlank
	@Size(min = 11, max = 14)
	private String cpf;
	@Enumerated(EnumType.STRING)
	private Escolaridade escolaridade;
	@Enumerated(EnumType.STRING)
	private VagaDesejada vagaDesejada;
	@Enumerated(EnumType.STRING)
	private StatusCurriculo statusCurriculo;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Escolaridade getEscolaridade() {
		return escolaridade;
	}
	public void setEscolaridade(Escolaridade escolaridade) {
		this.escolaridade = escolaridade;
	}
	public VagaDesejada getVagaDesejada() {
		return vagaDesejada;
	}
	public void setVagaDesejada(VagaDesejada vagaDesejada) {
		this.vagaDesejada = vagaDesejada;
	}
	public StatusCurriculo getStatusCurriculo() {
		return statusCurriculo;
	}
	public void setStatusCurriculo(StatusCurriculo statusCurriculo) {
		this.statusCurriculo = statusCurriculo;
	}
}
