package com.seguro.auto.negocio.service;

import java.util.List;
import java.util.Optional;

import com.seguro.auto.negocio.Cliente;

public interface ClienteService {
	
    public Cliente salvar( Cliente cliente) throws Exception;
	
	public String remover(String cpf)  throws Exception;
	
    public Optional<Cliente> consultar(String cpf);
	
	public List<Cliente> listar();
}
