package br.com.casamagalhaes.workshop.desafio.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Itens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "O nome do produto precisa ser definido.")
    private String descricao;
    @NotNull(message = "O valor do produto deve ser definido.")
    private Double precoUnitario;
    @NotNull(message = "O Produto deve conter pelo menos 1 unidade.")
    private int quantidade;

    public Itens() {
    }

    public Itens(@NotNull(message = "O nome do produto precisa ser definido.") String descricao, @NotNull(message = "O valor do produto deve ser definido.") Double precoUnitario, @NotNull(message = "O Produto deve conter pelo menos 1 unidade.") int quantidade) {
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
    }

    public Itens(Long id, @NotNull(message = "O nome do produto precisa ser definido.") String descricao, @NotNull(message = "O valor do produto deve ser definido.") Double precoUnitario, @NotNull(message = "O Produto deve conter pelo menos 1 unidade.") int quantidade) {
        this.id = id;
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
    }

    public double valorTotal() {
        return precoUnitario * quantidade;
    }
}
