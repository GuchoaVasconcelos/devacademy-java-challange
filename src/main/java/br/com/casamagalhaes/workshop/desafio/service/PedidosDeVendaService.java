package br.com.casamagalhaes.workshop.desafio.service;


import br.com.casamagalhaes.workshop.desafio.model.PedidosDeVenda;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PedidosDeVendaService {

    PedidosDeVenda addPedido(PedidosDeVenda novoPedido);
    void rmvPedido(Long id);
    PedidosDeVenda attPedido(Long idPedidoAntigo, PedidosDeVenda novoPedido);
    List<PedidosDeVenda> listarPedidos();
    Page<PedidosDeVenda> listarPedidosPaginados(Integer numeroPagina, Integer tamanhoPagina);
    PedidosDeVenda listarPedido(Long id);
    boolean attStatus(Long id, String status);
}
