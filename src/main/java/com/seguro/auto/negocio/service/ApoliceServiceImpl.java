package com.seguro.auto.negocio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seguro.auto.negocio.Apolice;
import com.seguro.auto.negocio.Cliente;
import com.seguro.auto.negocio.mongodb.ApoliceRepository;
import com.seguro.auto.negocio.mongodb.ClienteRepository;

@Service
public class ApoliceServiceImpl implements ApoliceService {
	
	@Autowired
	private ApoliceRepository apoliceRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public Apolice salvar(Apolice apolice) throws Exception {
		boolean existsById = false;
		if ( apolice != null && apolice.getCpf() != null ) {
			Optional<Cliente> cliente = clienteRepository.findById(apolice.getCpf());
			if ( cliente.isPresent() == false) {
				throw new Exception("Cliente não encontrado pelo CPF");
			}
		}
		if ( apolice != null && apolice.getNumeroApolice() != null ) {
		    existsById = apoliceRepository.existsById(apolice.getNumeroApolice());
		}
		if ( existsById ) {
			apolice = apoliceRepository.save(apolice);
		} else {
			apolice = apoliceRepository.insert(apolice);
		}
		return apolice;
	}

	@Override
	public void remover(Long numeroApolice) {
		Optional<Apolice> apolice = apoliceRepository.findById(numeroApolice);
		if ( apolice.isPresent() ) {
     		apoliceRepository.delete(apolice.get());
		}
	}

	@Override
	public Optional<Apolice> consultar(Long numeroApolice) {
		Optional<Apolice> apolice = apoliceRepository.findById(numeroApolice);
		return apolice;
	}

	@Override
	public List<Apolice> listar() {
		List<Apolice> apolices = apoliceRepository.findAll();
		return apolices;
	}

	@Override
	public Apolice findByPlacaVeiculo(String placaVeiculo) {
		Apolice apolice = apoliceRepository.findByPlacaVeiculo(placaVeiculo);
		return apolice;
	}

}
