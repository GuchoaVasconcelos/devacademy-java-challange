package br.com.casamagalhaes.workshop.desafio.controllers;


import br.com.casamagalhaes.workshop.desafio.model.PedidosDeVenda;
import br.com.casamagalhaes.workshop.desafio.service.PedidosDeVendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/pedidos")
public class PedidoController {


    @Autowired
    private PedidosDeVendaService PedidosDeVendaService;

//    @GetMapping(path = {"/",""})
//    public List<PedidosDeVenda> listarPedidos(){
//        return PedidosDeVendaService.listarPedidos();
//    }

    @GetMapping(path = {"/",""})
    public Page<PedidosDeVenda> listarPedidosPaginado(@RequestParam Integer numeroPagina, @RequestParam Integer tamanhoPagina){
        return PedidosDeVendaService.listarPedidosPaginados(numeroPagina, tamanhoPagina);
    }

    @PostMapping(path = {"/",""})
    public PedidosDeVenda addPedido(@Valid PedidosDeVenda novoPedido){
        return PedidosDeVendaService.addPedido(novoPedido);
    }
    @DeleteMapping(path = {"/{idPedido}",""})
    public void rmvPedido(@PathVariable Long idPedido){
        PedidosDeVendaService.rmvPedido(idPedido);
    }
    @PostMapping(path = {"/{idPedidoAntigo}"})
    public void attPedido(@PathVariable Long idPedidoAntigo, @Valid PedidosDeVenda novoPedido){
        PedidosDeVendaService.attPedido(idPedidoAntigo,novoPedido);
    }

    @PostMapping(path = {"/{id}/status"})
    public boolean attStatus(@PathVariable Long id, String novoStatus){
        return PedidosDeVendaService.attStatus(id, novoStatus);
    }

}
