package br.com.tech4me.pedidosnike.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.tech4me.pedidosnike.model.Pedidos;

public interface PedidosRepository extends MongoRepository <Pedidos, String> {
    
}
