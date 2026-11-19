package com.uem.Service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExchangeRateServiceTest {
    @Test
    void parsesValidRate() {
        var parsed = ExchangeRateService.parseRate(
                "{\"date\":\"2026-11-12\",\"base\":\"EUR\",\"quote\":\"USD\",\"rate\":1.17}");
        assertEquals(1.17, parsed.rate(), 0.00001);
        assertEquals("2026-11-12", parsed.date());
    }

    @Test
    void rejectsWrongPairAndInvalidRate() {
        assertThrows(IllegalArgumentException.class, () -> ExchangeRateService.parseRate(
                "{\"date\":\"2026-11-12\",\"base\":\"USD\",\"quote\":\"EUR\",\"rate\":1.17}"));
        assertThrows(IllegalArgumentException.class, () -> ExchangeRateService.parseRate(
                "{\"date\":\"2026-11-12\",\"base\":\"EUR\",\"quote\":\"USD\",\"rate\":0}"));
    }
}
