package br.com.banco.APIPagamentos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //cria os getters and setters e o to string automaticamente
@AllArgsConstructor //cria o construtor com as propriedades que criar de pagamento
@NoArgsConstructor //cria um construtor vazio, sem argumento
@Builder //ajuda na criação de objetos pagamento
@Entity
public class FormaPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "parcelas", nullable = false)
    private int parcelas;
}
