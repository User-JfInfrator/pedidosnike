package br.com.tech4me.pedidosnike.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4me.pedidosnike.httpclient.ProdutoClient;
import br.com.tech4me.pedidosnike.model.Pedidos;
import br.com.tech4me.pedidosnike.model.Produto;
import br.com.tech4me.pedidosnike.repository.PedidosRepository;
import br.com.tech4me.pedidosnike.shared.PedidoCompletoDto;
import br.com.tech4me.pedidosnike.shared.PedidoDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class PedidosServiceImpl implements PedidosService {
    @Autowired
    private PedidosRepository repositorio;

    @Autowired
    private ProdutoClient pdtClient;

    Produto produto;

    @Override
    public List<PedidoDto> obterTodosPedidos() {
        return repositorio.findAll().stream().map(p -> new PedidoDto(p.getIdPedidos(), p.getNomeCliente())).toList();
    }

    @CircuitBreaker(name = "obterPorId", fallbackMethod = "fallbackobterPorId")
    @Override
    public Optional<PedidoCompletoDto> obterPorId(String id) {
        Optional<Pedidos> pedido = repositorio.findById(id);
        if(pedido.isPresent()){
            Produto produto = pdtClient.obterProduto(pedido.get().getIdProduto());
            PedidoCompletoDto pedidoCompleto = new PedidoCompletoDto(pedido.get().getIdPedidos(), pedido.get().getNomeCliente(), pedido.get().getIdProduto(), produto);
            return Optional.of(pedidoCompleto);
        }
        else{
            return Optional.empty();
        }
    }

    public Optional<PedidoCompletoDto> fallbackobterPorId(String codigo) {
        Optional<Pedidos> pedido = repositorio.findById(codigo);
        if(pedido.isPresent()){
            
            PedidoCompletoDto pedidoCompleto = new PedidoCompletoDto(pedido.get().getIdPedidos(), pedido.get().getNomeCliente(), pedido.get().getIdProduto(), null);
            return Optional.of(pedidoCompleto);
        }
        else{
            return Optional.empty();
        }
    }

    @Override
    public PedidoCompletoDto cadastrarPedido(PedidoCompletoDto dto) {
        Pedidos pedido = new Pedidos(dto);
        repositorio.save(pedido);

        return new PedidoCompletoDto(pedido.getIdPedidos(), pedido.getNomeCliente(),pedido.getIdProduto(), pdtClient.obterProduto(pedido.getIdProduto()));
    }

    @Override
    public Optional<PedidoCompletoDto> atualizarPorId(String id, PedidoCompletoDto dto) {
        Optional<Pedidos> pedido = repositorio.findById(id);

        if(pedido.isPresent()){
            Pedidos pedidoAtualizado = new Pedidos(dto);
            pedidoAtualizado.setIdPedidos(id);
            repositorio.save(pedidoAtualizado);
            return Optional.of(new PedidoCompletoDto(pedidoAtualizado.getIdPedidos(), pedidoAtualizado.getNomeCliente(), pedidoAtualizado.getIdProduto(), pdtClient.obterProduto(pedido.get().getIdProduto())));
        }
        else{
            return Optional.empty();
        }
    }

    @Override
    public void excluirPorId(String id) {
        repositorio.deleteById(id);
    }
    
}

