
package com.uem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.uem.Controller.OrderController;
import com.uem.Model.Order;
import com.uem.View.OrderView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.SwingUtilities;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Main {

    private static final Logger log =
            LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        Gson gson = new Gson();

        // Leemos los pedidos desde el JSON
        try (InputStream input =
                Main.class.getResourceAsStream("/orders.json")) {

            if (input == null) {
                log.error("No se ha encontrado orders.json en resources");
                return;
            }

            try (Reader reader = new InputStreamReader(
                    input, StandardCharsets.UTF_8)) {

                List<Order> orders = gson.fromJson(
                        reader,
                        new TypeToken<List<Order>>() {}.getType()
                );

                if (orders == null) {
                    log.error("El archivo orders.json esta vacio");
                    return;
                }

                // Registramos los pedidos cargados
                for (Order order : orders) {
                    log.debug("Loaded order: {}", order.getId());
                }

                // Initialize MVC
                SwingUtilities.invokeLater(() -> {

                    OrderView view = new OrderView();

                    new OrderController(view, orders);
                });
            }

        } catch (Exception e) {

            log.error(
                "Error al leer los pedidos del archivo JSON", e
            );
        }
    }
}
