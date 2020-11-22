package com.seguro.auto.negocio;


import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Document(collection = "Cliente")
public class Cliente {
	@Id
	@Indexed(unique = true)
	private String cpf;
	@NotBlank(message = "Nome completo é obrigatório")
	private String nomeCompleto;
	@NotBlank(message = "Cidade é obrigatório")
	private String cidade;
	@NotBlank(message = "UF é obrigatório")
	private String uf; 

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
}
