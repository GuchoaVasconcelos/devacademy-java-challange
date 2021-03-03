package br.com.casamagalhaes.workshop.desafio.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class PedidosDeVenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;
    private float valorTotalProdutos;
    private float taxa;
    private float valorTotal;
    @OneToMany
    private List<Itens> itens = new ArrayList<>();
}
