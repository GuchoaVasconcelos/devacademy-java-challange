package br.com.casamagalhaes.workshop.desafio.service;


import br.com.casamagalhaes.workshop.desafio.model.PedidosDeVenda;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PedidosDeVendaService {

    PedidosDeVenda adicionar(PedidosDeVenda novoPedido);
    void remover(Long id);
    void atualizar(Long idPedidoAntigo, PedidosDeVenda novoPedido);
    List<PedidosDeVenda> listarPedidos();
    Page<PedidosDeVenda> listarPedidosPaginados(Integer numeroPagina, Integer tamanhoPagina);
    PedidosDeVenda listarPedido(Long id);
    boolean atualizarStatus(Long id, String status);
}
