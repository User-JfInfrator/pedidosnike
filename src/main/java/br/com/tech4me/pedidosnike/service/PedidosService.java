package br.com.tech4me.pedidosnike.service;

import java.util.List;
import java.util.Optional;

import br.com.tech4me.pedidosnike.shared.PedidoCompletoDto;
import br.com.tech4me.pedidosnike.shared.PedidoDto;

public interface PedidosService {
    List<PedidoDto> obterTodosPedidos();
    Optional<PedidoCompletoDto> obterPorId(String id);
    PedidoCompletoDto cadastrarPedido(PedidoCompletoDto dto);
    Optional<PedidoCompletoDto> atualizarPorId(String id, PedidoCompletoDto dto);
    void excluirPorId(String id);
}
