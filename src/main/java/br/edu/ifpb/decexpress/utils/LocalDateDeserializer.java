package br.edu.ifpb.decexpress.utils;

import java.io.IOException;
import java.io.Serial;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class LocalDateDeserializer extends StdDeserializer<LocalDate> {

    @Serial
    private static final long serialVersionUID = -8207825633931172320L;

    public LocalDateDeserializer() {
        this(null);
    }

    protected LocalDateDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public LocalDate deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);
        String dateText = node.textValue();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateText, formatter);
    }
}
