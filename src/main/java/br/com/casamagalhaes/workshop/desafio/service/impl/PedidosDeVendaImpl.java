package br.com.casamagalhaes.workshop.desafio.service.impl;

import br.com.casamagalhaes.workshop.desafio.model.Itens;
import br.com.casamagalhaes.workshop.desafio.model.PedidosDeVenda;
import br.com.casamagalhaes.workshop.desafio.repository.PedidosDeVendaRepository;
import br.com.casamagalhaes.workshop.desafio.service.PedidosDeVendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PedidosDeVendaImpl implements PedidosDeVendaService {

    @Autowired
    private PedidosDeVendaRepository repository;

    public void calcularValorTotal(PedidosDeVenda pedidosDeVenda) {
        Double valorTotalProdutos = 0.0;
        for (Itens item : pedidosDeVenda.getItens()) {
            valorTotalProdutos += item.valorTotal();
        }

        pedidosDeVenda.setValorTotalProdutos(valorTotalProdutos);
        pedidosDeVenda.setValorTotal(valorTotalProdutos + pedidosDeVenda.getTaxa());
    }

    @Override
    public PedidosDeVenda adicionar(PedidosDeVenda novoPedido) {
        calcularValorTotal(novoPedido);
        return repository.save(novoPedido);

    }

    @Override
    public void remover(Long removerPedido) {
        repository.deleteById(removerPedido);
    }

    @Override

    public void atualizar(Long id, PedidosDeVenda novoPedido) {

        PedidosDeVenda pedidoExistente = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        novoPedido.setPedido(pedidoExistente.getPedido());
        novoPedido.setStatus(pedidoExistente.getStatus());
        List<Itens> itensVelhos = pedidoExistente.getItens();
        List<Itens> itensNovos = novoPedido.getItens();

        for(int i = 0; i< itensNovos.size(); i++){
            itensNovos.get(i).setId(itensVelhos.get(i).getId());
        }

        novoPedido.setItens(itensNovos);



        calcularValorTotal(novoPedido);
        repository.saveAndFlush(novoPedido);
    }

    @Override
    public List<PedidosDeVenda> listarPedidos() {
        return repository.findAll();
    }


    public Page<PedidosDeVenda> listarPedidosPaginados(Integer numeroPagina, Integer tamanhoPagina) {
        Pageable pageable = PageRequest.of(numeroPagina, tamanhoPagina);
        return repository.findAll(pageable);
    }

    @Override
    public PedidosDeVenda listarPedido(Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }


    @Override
    public boolean atualizarStatus(Long id, String novoStatus) {
        PedidosDeVenda pedidosDeVenda = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        switch (novoStatus) {
            case "CANCELADO":
                if (pedidosDeVenda.getStatus().equals("EM_ROTA") ||
                        pedidosDeVenda.getStatus().equals("ENTREGUE") ||
                        pedidosDeVenda.getStatus().equals("CANCELADO")) {
                    return false;
                } else {
                    pedidosDeVenda.setStatus(novoStatus);
                }
                break;
            case "EM_ROTA":
                if (pedidosDeVenda.getStatus().equals("PRONTO")) {
                    pedidosDeVenda.setStatus(novoStatus);
                } else {
                    return false;
                }
                break;
            case "ENTREGUE":
                if (pedidosDeVenda.getStatus().equals("EM_ROTA")) {
                    pedidosDeVenda.setStatus(novoStatus);
                } else {
                    return false;
                }
                break;
        }

        pedidosDeVenda.setStatus(novoStatus);
        return true;
    }
}
