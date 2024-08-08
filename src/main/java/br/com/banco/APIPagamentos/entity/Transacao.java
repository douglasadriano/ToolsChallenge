package br.com.banco.APIPagamentos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Transacao implements Serializable {
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
