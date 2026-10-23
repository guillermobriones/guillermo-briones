
package com.uem.Controller;

import com.uem.Model.Article;
import com.uem.Model.Order;
import com.uem.View.OrderView;

import java.util.List;

public class OrderController {

    // Factor fijo utilizado en el ejemplo del enunciado
    private static final double USD_RATE = 1.162976;

    private final OrderView view;
    private final List<Order> orders;

    // Constructor del controlador
    public OrderController(OrderView view, List<Order> orders) {
        this.view = view;
        this.orders = orders;

        // Conectamos el boton de buscar
        view.addSearchListener(e -> searchOrder());

        // Mostramos la ventana
        view.setVisible(true);
    }

    // Busca un pedido por su identificador
    private void searchOrder() {

        String id = view.getOrderId();

        for (Order order : orders) {

            if (order.getId() != null &&
                    order.getId().equalsIgnoreCase(id)) {

                view.showOrder(formatOrder(order));
                return;
            }
        }

        // Si no existe el pedido
        view.showOrder("Order not found.");
    }

    // Prepara la informacion del pedido
    private String formatOrder(Order order) {

        StringBuilder result = new StringBuilder();

        result.append("Order ")
              .append(order.getId())
              .append(":\n");

        // Recorremos los articulos del pedido
        for (Article article : order.getArticles()) {

            double discounted = article.GetDiscountedamount();

            result.append(article.getNombre())
                  .append(" x")
                  .append(article.getCantidad())
                  .append(" @ ")
                  .append(article.getPrecio())
                  .append("$")
                  .append(" (discount ")
                  .append(article.getDescuento())
                  .append("%)")
                  .append(" = ")
                  .append(discounted)
                  .append("$\n");
        }

        // Utilizamos los metodos del modelo
        double gross = order.GetGrossTotal();
        double discounted = order.GetDiscountedTotal();

        result.append("Gross total: ")
              .append(gross)
              .append("$\n");

        result.append("Discounted total: ")
              .append(discounted)
              .append("$\n");

        result.append("Gross total (USD): ")
              .append(gross * USD_RATE)
              .append("$\n");

        result.append("Discounted total (USD): ")
              .append(discounted * USD_RATE)
              .append("$");

        return result.toString();
    }
}
