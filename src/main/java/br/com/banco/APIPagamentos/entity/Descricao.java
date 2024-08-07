package br.com.banco.APIPagamentos.entity;

import br.com.banco.APIPagamentos.util.DoubleSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data //cria os getters and setters e o to string automaticamente
@AllArgsConstructor //cria o construtor com as propriedades que criar de pagamento
@NoArgsConstructor //cria um construtor vazio, sem argumento
@Builder //ajuda na criação de objetos pagamento
@Entity
public class Descricao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "valor", nullable = false)
    @JsonSerialize(using = DoubleSerializer.class)
    private double valor;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Column(name = "estabelecimento", nullable = false)
    private String estabelecimento;

    @Column(name = "nsu", nullable = true)
    private Long nsu;

    @Column(name = "codigo_autorizacao", nullable = true)
    private Long codigoAutorizacao;

    @Column(name = "status", nullable = true)
    private String status;
}
