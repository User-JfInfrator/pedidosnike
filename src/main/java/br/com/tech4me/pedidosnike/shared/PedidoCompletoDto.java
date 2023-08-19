package br.com.tech4me.pedidosnike.shared;

import br.com.tech4me.pedidosnike.model.Produto;

public record PedidoCompletoDto(String idPedidos, String nomeCliente, String idProduto, Produto produto) {
    
}
