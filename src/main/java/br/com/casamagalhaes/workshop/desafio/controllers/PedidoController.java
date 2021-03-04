package br.com.casamagalhaes.workshop.desafio.controllers;


import br.com.casamagalhaes.workshop.desafio.model.PedidosDeVenda;
import br.com.casamagalhaes.workshop.desafio.service.PedidosDeVendaService;
import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/pedidos")
public class PedidoController {


    @Autowired
    private PedidosDeVendaService PedidosDeVendaService;

    @GetMapping(path = {"/",""})
    public List<PedidosDeVenda> listarPedidos(){
        return PedidosDeVendaService.listarPedidos();
    }

//    @GetMapping(path = {"/",""})
//    public Page<PedidosDeVenda> listarPedidosPaginado(@RequestParam Integer numeroPagina, @RequestParam Integer tamanhoPagina){
//        return PedidosDeVendaService.listarPedidosPaginados(numeroPagina, tamanhoPagina);
//    }

    @PostMapping(path = {"/",""})
    @ResponseStatus(HttpStatus.CREATED)
    public PedidosDeVenda adicionarPedido(@Valid @RequestBody PedidosDeVenda novoPedido){
        System.out.println(novoPedido);
        return PedidosDeVendaService.adicionar(novoPedido);
    }


    @DeleteMapping(path = {"/{id}",""})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void rmvPedido(@PathVariable Long id){
        PedidosDeVendaService.remover(id);
    }


    @PutMapping(path = {"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void attPedido(@PathVariable Long id, @Valid @RequestBody PedidosDeVenda novoPedido){
        PedidosDeVendaService.atualizar(id,novoPedido);
    }

    @PostMapping(path = {"/{id}/status"})
    @ResponseStatus(HttpStatus.OK)
    public boolean attStatus(@PathVariable Long id, String novoStatus){
        return PedidosDeVendaService.atualizarStatus(id, novoStatus);
    }

}
