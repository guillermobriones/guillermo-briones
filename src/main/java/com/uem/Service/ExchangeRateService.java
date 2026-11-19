package com.uem.Service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

/**
 * Consulta la última cotización de referencia EUR/USD publicada por Frankfurter.
 * No requiere clave API. Los datos se actualizan cuando publican los proveedores;
 * no es una cotización bursátil en tiempo real.
 */
public class ExchangeRateService {
    private static final URI API_URL =
            URI.create("https://api.frankfurter.dev/v2/rate/eur/usd");

    private final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .build();

    public CompletableFuture<ExchangeRate> fetchEurUsdRate() {
        HttpRequest request = HttpRequest.newBuilder(API_URL)
                .timeout(Duration.ofSeconds(10))
                .header("Accept", "application/json")
                .GET()
                .build();

        return client.sendAsync(request,
                        HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8))
                .thenApply(response -> {
                    if (response.statusCode() != 200) {
                        throw new IllegalStateException(
                                "El servicio de divisas devolvió HTTP "
                                        + response.statusCode());
                    }
                    return parseRate(response.body());
                });
    }

    // También lo usan las pruebas unitarias sin necesitar conexión a Internet.
    static ExchangeRate parseRate(String json) {
        JsonObject data = JsonParser.parseString(json).getAsJsonObject();
        String base = data.get("base").getAsString();
        String quote = data.get("quote").getAsString();
        double rate = data.get("rate").getAsDouble();
        String date = data.get("date").getAsString();

        if (!"EUR".equalsIgnoreCase(base) || !"USD".equalsIgnoreCase(quote)
                || !Double.isFinite(rate) || rate <= 0 || date.isBlank()) {
            throw new IllegalArgumentException("Respuesta EUR/USD no válida");
        }
        return new ExchangeRate(rate, date);
    }

    public record ExchangeRate(double rate, String date) { }
}
