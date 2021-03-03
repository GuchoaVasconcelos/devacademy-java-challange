package br.com.casamagalhaes.workshop.desafio.repository;

import br.com.casamagalhaes.workshop.desafio.model.Endereço;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EndereçoRepository extends JpaRepository<Endereço, Long> {

}
