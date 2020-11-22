package com.seguro.auto.negocio.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.seguro.auto.negocio.Cliente;

public interface ClienteRepository extends MongoRepository<Cliente, String> {

}
