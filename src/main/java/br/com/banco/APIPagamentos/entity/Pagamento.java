package br.com.banco.APIPagamentos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data //cria os getters and setters e o to string automaticamente
@AllArgsConstructor //cria o construtor com as propriedades que criar de pagamento
@NoArgsConstructor //cria um construtor vazio, sem argumento
@Builder //ajuda na criação de objetos pagamento
@Entity //para informar que é uma entidade de banco de dados
public class Pagamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "cartao", nullable = false)
    private String cartao;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "descricao_id", referencedColumnName = "id")
    private Descricao descricao;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "forma_pagamento_id", referencedColumnName = "id")
    private FormaPagamento formaPagamento;
}
