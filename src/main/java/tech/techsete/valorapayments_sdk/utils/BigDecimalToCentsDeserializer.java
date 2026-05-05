package tech.techsete.valorapayments_sdk.utils;

import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;

import java.math.BigDecimal;

public class BigDecimalToCentsDeserializer extends ValueDeserializer<Integer> {

    @Override
    public Integer deserialize(JsonParser p, DeserializationContext ctxt) {
        try {
            String value = p.getText();
            if (value == null || value.trim().isEmpty()) {
                return null;
            }
            BigDecimal bd = new BigDecimal(value);
            return bd.multiply(new BigDecimal("100")).intValueExact();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao desserializar valor BigDecimal para Integer (centavos).", e);
        }
    }
}
