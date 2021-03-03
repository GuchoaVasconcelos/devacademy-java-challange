package br.com.casamagalhaes.workshop.desafio.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Endereço {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rua;
    private Integer numero;
    private String complemento;
    private String bairro;
}
