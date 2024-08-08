package br.com.banco.APIPagamentos.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class FormaPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @Column(name = "tipo", nullable = false)
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @Column(name = "parcelas", nullable = false)
    private int parcelas;

    public enum Tipo {
        AVISTA("AVISTA"),
        PARCELADO_LOJA("PARCELADO LOJA"),
        PARCELADO_EMISSOR("PARCELADO EMISSOR");

        private final String displayName;

        Tipo(String displayName) {
            this.displayName = displayName;
        }

        @JsonValue
        public String getDisplayName() {
            return displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }

        @JsonCreator
        public static Tipo fromDisplayName(String displayName) {
            for (Tipo tipo : Tipo.values()) {
                if (tipo.getDisplayName().equalsIgnoreCase(displayName)) {
                    return tipo;
                }
            }
            throw new IllegalArgumentException("Tipo de pagamento inválido: " + displayName);
        }
    }

}
