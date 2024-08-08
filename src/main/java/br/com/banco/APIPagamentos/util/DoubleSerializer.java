package br.com.banco.APIPagamentos.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

// A classe DoubleSerializer extende StdSerializer<Double> e é usada para serializar valores do tipo Double
// em um formato específico durante a conversão para JSON.
public class DoubleSerializer extends StdSerializer<Double> {

    // Um DecimalFormat estático para formatar os números Double.
    private static final DecimalFormat df;

    // Bloco estático que inicializa o DecimalFormat com símbolos específicos para o locale US,
    // garantindo que o separador decimal seja um ponto.
    static {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setDecimalSeparator('.');
        df = new DecimalFormat("0.00", symbols);
    }

    // Construtor sem argumentos, necessário para a serialização.
    // Chama o construtor com argumento null.
    public DoubleSerializer() {
        this(null);
    }

    // Construtor que aceita um Class<Double> e passa para o construtor da superclasse.
    public DoubleSerializer(Class<Double> t) {
        super(t);
    }

    // Método sobrescrito da classe StdSerializer.
    // Este método é chamado durante a serialização de um objeto Double para JSON.
    @Override
    public void serialize(Double value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        // Usa o DecimalFormat df para formatar o valor Double e escreve como uma String no JSON.
        gen.writeString(df.format(value));
    }
}
