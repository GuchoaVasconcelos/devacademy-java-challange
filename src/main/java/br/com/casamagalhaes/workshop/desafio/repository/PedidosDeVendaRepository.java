package br.com.casamagalhaes.workshop.desafio.repository;

import br.com.casamagalhaes.workshop.desafio.model.PedidosDeVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidosDeVendaRepository extends JpaRepository<PedidosDeVenda, Long> {


}
