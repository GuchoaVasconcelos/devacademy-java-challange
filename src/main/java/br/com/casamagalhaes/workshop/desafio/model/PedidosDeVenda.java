package br.com.casamagalhaes.workshop.desafio.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter
@Setter
public class PedidosDeVenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pedido;

    // @OneToOne(cascade = CascadeType.ALL)
    // private Cliente cliente;
    private String nomeCliente;
    private String endereco;
    private String telefone;
    private Double valorTotalProdutos;
    private Double taxa;
    private Double valorTotal;
    private String status = "PENDENTE";
    @OneToMany(cascade = CascadeType.ALL)
    @Size(min = 1, message = "Deve possuir pelo menos 1 item.")
    private List<Itens> itens = new ArrayList<Itens>();

    public PedidosDeVenda(Long pedido, String nomeCliente, String endereco, String telefone, Double taxa, String status, @Size(min = 1, message = "Deve possuir pelo menos 1 item.") List<Itens> itens) {
        this.pedido = pedido;
        this.nomeCliente = nomeCliente;
        this.endereco = endereco;
        this.telefone = telefone;
        this.taxa = taxa;
        this.status = status;
        this.itens = itens;
    }

    public PedidosDeVenda() {
    }
}
