package tech.techsete.valorapayments_sdk.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.math.BigDecimal;

public class BigDecimalToCentsDeserializer extends JsonDeserializer<Integer> {

    @Override
    public Integer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
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
