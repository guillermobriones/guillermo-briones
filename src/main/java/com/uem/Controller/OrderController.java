package com.uem.Controller;

import com.uem.Model.Article;
import com.uem.Model.Order;
import com.uem.Service.ExchangeRateService;
import com.uem.Service.ExchangeRateService.ExchangeRate;
import com.uem.View.OrderView;

import javax.swing.SwingUtilities;
import java.util.List;
import java.util.Locale;

public class OrderController {
    private final OrderView view;
    private final List<Order> orders;
    private final ExchangeRateService exchangeRateService;
    private int searchSequence = 0;

    public OrderController(OrderView view, List<Order> orders) {
        this.view = view;
        this.orders = orders;
        this.exchangeRateService = new ExchangeRateService();

        view.addSearchListener(e -> searchOrder());
        view.setVisible(true);
    }

    private void searchOrder() {
        int currentSearch = ++searchSequence;
        String id = view.getOrderId();

        if (id.isBlank()) {
            view.showOrder("Introduce el ID de un pedido.");
            return;
        }

        Order found = null;
        for (Order order : orders) {
            if (order.getId() != null && order.getId().equalsIgnoreCase(id)) {
                found = order;
                break;
            }
        }

        if (found == null) {
            view.showOrder("Order not found.");
            return;
        }

        // Los importes originales de orders.json se consideran expresados en EUR.
        double grossEur = found.GetGrossTotal();
        double discountedEur = found.GetDiscountedTotal();
        String euroDetails = formatOrder(found, grossEur, discountedEur);
        view.showOrder(euroDetails + "\nConsultando último cambio EUR/USD publicado...");

        // La petición HTTP se hace sin bloquear el hilo de la interfaz de Swing.
        exchangeRateService.fetchEurUsdRate().whenComplete((rate, error) ->
                SwingUtilities.invokeLater(() -> {
                    // Una búsqueda anterior no debe sobrescribir una más reciente.
                    if (currentSearch != searchSequence || !view.isDisplayable()) {
                        return;
                    }
                    if (error != null) {
                        view.showOrder(euroDetails
                                + "\nConversión a USD no disponible (comprueba Internet)."
                                + "\nInténtalo de nuevo con Search.");
                    } else {
                        view.showOrder(euroDetails
                                + formatDollarTotals(grossEur, discountedEur, rate));
                    }
                }));
    }

    private String formatOrder(Order order, double grossEur, double discountedEur) {
        StringBuilder text = new StringBuilder("Order ")
                .append(order.getId()).append(":\n");

        for (Article article : order.getArticles()) {
            text.append(String.format(Locale.US,
                    "%s x%d @ %.2f EUR (discount %.1f%%) = %.2f EUR%n",
                    article.getNombre(), article.getCantidad(), article.getPrecio(),
                    article.getDescuento(), article.GetDiscountedamount()));
        }
        text.append(String.format(Locale.US,
                "Gross total (EUR): %.2f EUR%nDiscounted total (EUR): %.2f EUR%n",
                grossEur, discountedEur));
        return text.toString();
    }

    private String formatDollarTotals(double grossEur, double discountedEur,
                                      ExchangeRate rate) {
        return String.format(Locale.US,
                "Exchange rate (published %s): 1 EUR = %.6f USD%n"
                        + "Gross total (USD): %.2f USD%n"
                        + "Discounted total (USD): %.2f USD",
                rate.date(), rate.rate(), grossEur * rate.rate(),
                discountedEur * rate.rate());
    }
}
