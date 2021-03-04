package br.com.casamagalhaes.workshop.desafio.service.impl;

import br.com.casamagalhaes.workshop.desafio.model.PedidosDeVenda;
import br.com.casamagalhaes.workshop.desafio.repository.PedidosDeVendaRepository;
import br.com.casamagalhaes.workshop.desafio.service.PedidosDeVendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
    public class PedidosDeVendaImpl implements PedidosDeVendaService {

    @Autowired
    private PedidosDeVendaRepository repository;

    @Override
    public PedidosDeVenda addPedido(PedidosDeVenda novoPedido) {
        return repository.saveAndFlush(novoPedido);

    }

    @Override
    public void rmvPedido(Long removerPedido) {
        repository.deleteById(removerPedido);
    }

    @Override
    public PedidosDeVenda attPedido(Long idPedidoAntigo, PedidosDeVenda novoPedido) {
        if(repository.existsById(idPedidoAntigo)){
            if(idPedidoAntigo.equals(novoPedido.getId())){
                return repository.saveAndFlush(novoPedido);
            }else{
                throw new UnsupportedOperationException("Pedido id: " + novoPedido.getId());
            }
        }
        else{
            throw new EntityNotFoundException("Produto id: " + novoPedido.getId());
        }
    }

    @Override
    public List<PedidosDeVenda> listarPedidos() {
        return repository.findAll();
    }


    public Page<PedidosDeVenda> listarPedidosPaginados(Integer numeroPagina, Integer tamanhoPagina){
        Pageable pageable = PageRequest.of(numeroPagina, tamanhoPagina);
        return repository.findAll(pageable);
    }

    @Override
    public PedidosDeVenda listarPedido(Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }


    @Override
    public boolean attStatus(Long id, String novoStatus) {
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
