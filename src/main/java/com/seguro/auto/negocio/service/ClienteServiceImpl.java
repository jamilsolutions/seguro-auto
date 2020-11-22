package com.seguro.auto.negocio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seguro.auto.negocio.Cliente;
import com.seguro.auto.negocio.mongodb.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public Cliente salvar(Cliente cliente) {
		boolean existsById = clienteRepository.existsById(cliente.getCpf());
		if ( existsById ) {
			cliente = clienteRepository.save(cliente);
		} else {
		    cliente = clienteRepository.insert(cliente);
		}
		return cliente;
	}

	@Override
	public void remover(String cpf) {
		Optional<Cliente> cliente = clienteRepository.findById(cpf);
		if ( cliente.isPresent() )
		clienteRepository.delete(cliente.get());
	}

	@Override
	public Optional<Cliente> consultar(String cpf) {
		Optional<Cliente> cliente = clienteRepository.findById(cpf);
		return cliente;
	}

	@Override
	public List<Cliente> listar() {
		List<Cliente> clientes = clienteRepository.findAll();
		return clientes;
	}

}
