package br.com.casamagalhaes.workshop.desafio.repository;


import br.com.casamagalhaes.workshop.desafio.model.Itens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItensRepository extends JpaRepository<Itens, Long> {
}
