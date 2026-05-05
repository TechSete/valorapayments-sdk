package tech.techsete.valorapayments_sdk.utils;

import org.junit.jupiter.api.Test;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BigDecimalToCentsDeserializerTest {

    private final BigDecimalToCentsDeserializer deserializer = new BigDecimalToCentsDeserializer();

    @Test
    void shouldConvertRealToCents() throws IOException {
        JsonParser p = mock(JsonParser.class);
        DeserializationContext ctxt = mock(DeserializationContext.class);

        when(p.getText()).thenReturn("5.00");
        assertEquals(500, deserializer.deserialize(p, ctxt));

        when(p.getText()).thenReturn("10.50");
        assertEquals(1050, deserializer.deserialize(p, ctxt));

        when(p.getText()).thenReturn("0.01");
        assertEquals(1, deserializer.deserialize(p, ctxt));
    }

    @Test
    void shouldReturnNullForEmptyValues() throws IOException {
        JsonParser p = mock(JsonParser.class);
        DeserializationContext ctxt = mock(DeserializationContext.class);

        when(p.getText()).thenReturn(null);
        assertNull(deserializer.deserialize(p, ctxt));

        when(p.getText()).thenReturn("");
        assertNull(deserializer.deserialize(p, ctxt));

        when(p.getText()).thenReturn("  ");
        assertNull(deserializer.deserialize(p, ctxt));
    }
}
