package com.seguro.auto.negocio.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.seguro.auto.negocio.Apolice;

public interface ApoliceRepository extends MongoRepository<Apolice, Long> {

	Apolice findByPlacaVeiculo(String placaVeiculo);
        
}
