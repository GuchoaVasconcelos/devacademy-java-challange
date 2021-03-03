package br.com.casamagalhaes.workshop.desafio.service.impl;

import br.com.casamagalhaes.workshop.desafio.model.PedidosDeVenda;
import br.com.casamagalhaes.workshop.desafio.repository.PedidosDeVendaRepository;
import br.com.casamagalhaes.workshop.desafio.service.PedidosDeVendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.Id;

@Service
public class PedidosDeVendaImpl implements PedidosDeVendaService {

    @Autowired
    private PedidosDeVendaRepository repository;

    @Override
    public PedidosDeVenda addPedido(PedidosDeVenda novoPedido) {
        return repository.save(novoPedido);

    }



    @Override
    public void rmvPedido(Long removerPedido) {
        repository.deleteById(removerPedido);
    }

    @Override
    public PedidosDeVenda attPedido(PedidosDeVenda antigoPedido, PedidosDeVenda novoPedido) {
    repository.findById(antigoPedido);
    }

    @Override
    public PedidosDeVenda listarPedidos() {
        return null;
    }
}
