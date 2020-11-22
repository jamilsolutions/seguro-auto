package com.seguro.auto.negocio.service;

import java.util.List;
import java.util.Optional;

import com.seguro.auto.negocio.Apolice;

public interface ApoliceService {
	
    Apolice salvar(Apolice apolice) throws Exception;
	
    void remover(Long numeroApolice);
	
    Optional<Apolice> consultar(Long numeroApolice);
	
    List<Apolice> listar();

	Apolice findByPlacaVeiculo(String placaVeiculo);
}
