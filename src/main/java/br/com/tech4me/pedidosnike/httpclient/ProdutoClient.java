package br.com.tech4me.pedidosnike.httpclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.tech4me.pedidosnike.model.Produto;

@FeignClient("lojanike")
public interface ProdutoClient {
    
    @RequestMapping(method = RequestMethod.GET, value = "/produto/{id}")
    Produto obterProduto(@PathVariable String id);
}
