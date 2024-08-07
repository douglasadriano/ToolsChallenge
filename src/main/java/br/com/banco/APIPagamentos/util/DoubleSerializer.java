package br.com.banco.APIPagamentos.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class DoubleSerializer extends StdSerializer<Double> {

    private static final DecimalFormat df;

    static {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setDecimalSeparator('.');
        df = new DecimalFormat("0.00", symbols);
    }

    public DoubleSerializer() {
        this(null);
    }

    public DoubleSerializer(Class<Double> t) {
        super(t);
    }

    @Override
    public void serialize(Double value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(df.format(value));
    }
}
