package com.seguro.auto.negocio.service;

import java.util.List;
import java.util.Optional;

import com.seguro.auto.negocio.Cliente;

public interface ClienteService {
	
    public Cliente salvar( Cliente cliente);
	
	public void remover(String cpf);
	
    public Optional<Cliente> consultar(String cpf);
	
	public List<Cliente> listar();
}
