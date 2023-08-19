package br.com.tech4me.pedidosnike.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.tech4me.pedidosnike.shared.PedidoCompletoDto;

@Document("pedido")
public class Pedidos {
    
    @Id
    private String idPedidos; 
    private String nomeCliente;
    private String idProduto;

    public Pedidos(){}

    public Pedidos(PedidoCompletoDto dto){
        this.idPedidos = dto.idPedidos();
        this.nomeCliente = dto.nomeCliente();
        this.idProduto = dto.idProduto();
    }
    

    
    public String getIdPedidos() {
        return idPedidos;
    }
    public void setIdPedidos(String idPedidos) {
        this.idPedidos = idPedidos;
    }
    public String getNomeCliente() {
        return nomeCliente;
    }
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
    public String getIdProduto() {
        return idProduto;
    }
    public void setIdProduto(String idProduto) {
        this.idProduto = idProduto;
    }
}
