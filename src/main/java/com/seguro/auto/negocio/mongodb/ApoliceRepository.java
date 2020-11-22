package com.seguro.auto.negocio.mongodb;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.seguro.auto.negocio.Apolice;

public interface ApoliceRepository extends MongoRepository<Apolice, Long> {

	List<Apolice> findByPlacaVeiculo(String placaVeiculo);

	Optional<Apolice> findTopFimVigenciaByCpfOrderByFimVigenciaDesc(String cpf);
        
}
