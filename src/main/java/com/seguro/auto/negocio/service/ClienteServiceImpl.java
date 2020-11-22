package com.seguro.auto.negocio.service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seguro.auto.negocio.Apolice;
import com.seguro.auto.negocio.Cliente;
import com.seguro.auto.negocio.mongodb.ApoliceRepository;
import com.seguro.auto.negocio.mongodb.ClienteRepository;
import com.seguro.auto.negocio.util.CPFUtil;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ApoliceRepository apoliceRepository;

	@Override
	public Cliente salvar(Cliente cliente) throws Exception {
		if ( CPFUtil.isValid(cliente.getCpf()) == false) {
			throw new Exception("O CPF informado não foi inválido.");
		}
		boolean existsById = clienteRepository.existsById(cliente.getCpf());
		if ( existsById ) {
			cliente = clienteRepository.save(cliente);
		} else {
		    cliente = clienteRepository.insert(cliente);
		}
		return cliente;
	}

	@Override
	public String remover(String cpf) throws Exception {
		if ( cpf != null ) {
			Optional<Apolice> apolice = apoliceRepository.findTopFimVigenciaByCpfOrderByFimVigenciaDesc(cpf);
			if ( apolice.isPresent() ) {
				if ( apolice.get().getFimVigencia().after(Calendar.getInstance().getTime())) {
					return "Erro ao excluir. Cliente possui apólice "+apolice.get().getNumeroApolice() + " vigente.";
				}
			}
		}
		Optional<Cliente> cliente = clienteRepository.findById(cpf);
		if ( cliente.isPresent() ) {
		    clienteRepository.delete(cliente.get());
		} else {
			throw new Exception("Cliente não encontrado pelo CPF");
		}
		return null;
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
