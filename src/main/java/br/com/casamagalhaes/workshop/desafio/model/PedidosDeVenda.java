package br.com.casamagalhaes.workshop.desafio.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class PedidosDeVenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pedido;

    //    @OneToOne(cascade = CascadeType.ALL)
    //    private Cliente cliente;
    private String nomeCliente;
    private String endereco;
    private String telefone;
    private Double valorTotalProdutos;
    private Double taxa;
    private Double valorTotal;
    private String status = "PENDENTE";
    @OneToMany(cascade=CascadeType.ALL)
    @Size(min = 1, message = "Deve possuir pelo menos 1 item.")
    private List<Itens> itens = new ArrayList<Itens>();
}


