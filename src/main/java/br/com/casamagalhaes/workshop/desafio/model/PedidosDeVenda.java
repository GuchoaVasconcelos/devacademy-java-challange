package br.com.casamagalhaes.workshop.desafio.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class PedidosDeVenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Cliente cliente;
    private float valorTotalProdutos;
    private float taxa;
    private float valorTotal;
    private String status;
    @OneToMany
    private List<Itens> itens = new ArrayList<>();
}
