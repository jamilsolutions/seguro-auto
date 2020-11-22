package com.seguro.auto.negocio;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Document(collection = "Apolice")
public class Apolice {	
	@Id 
	private Long numeroApolice;
	@NotNull(message = "In�cio vig�ncia � obrigat�rio")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date inicioVigencia;
	@NotNull(message = "Fim vig�ncia � obrigat�rio")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date fimVigencia;
	@NotBlank(message = "Placa do ve�culo � obrigat�rio")
	private String placaVeiculo;
	@NotNull(message = "Valor da ap�lice � obrigat�rio")
	private Double valorApolice;
	@NotBlank(message = "CPF do cliente � obrigat�rio")
	private String cpf;
	
	public Long getNumeroApolice() {
		return numeroApolice;
	}
	public void setNumeroApolice(Long numeroApolice) {
		this.numeroApolice = numeroApolice;
	}
	public Date getInicioVigencia() {
		return inicioVigencia;
	}
	public void setInicioVigencia(Date inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}
	public Date getFimVigencia() {
		return fimVigencia;
	}
	public void setFimVigencia(Date fimVigencia) {
		this.fimVigencia = fimVigencia;
	}
	public String getPlacaVeiculo() {
		return placaVeiculo;
	}
	public void setPlacaVeiculo(String placaVeiculo) {
		this.placaVeiculo = placaVeiculo;
	}
	public Double getValorApolice() {
		return valorApolice;
	}
	public void setValorApolice(Double valorApolice) {
		this.valorApolice = valorApolice;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
