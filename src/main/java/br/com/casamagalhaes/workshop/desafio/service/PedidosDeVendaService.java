package br.com.casamagalhaes.workshop.desafio.service;


import br.com.casamagalhaes.workshop.desafio.model.PedidosDeVenda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface PedidosDeVendaService {

    PedidosDeVenda addPedido(PedidosDeVenda novoPedido);
    void rmvPedido(Long id);
    PedidosDeVenda attPedido(PedidosDeVenda antigoPedido, PedidosDeVenda novoPedido);
    PedidosDeVenda listarPedidos();
}
