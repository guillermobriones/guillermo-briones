
package com.uem.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class OrderView extends JFrame {

    private final JTextField orderIdField;
    private final JButton searchButton;
    private final JTextArea resultArea;

    public OrderView() {

        setTitle("Order Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Panel para buscar pedidos
        JPanel searchPanel = new JPanel(
                new FlowLayout(FlowLayout.LEFT));

        searchPanel.add(new JLabel("Order ID:"));

        // Campo para introducir el ID
        orderIdField = new JTextField(10);
        searchPanel.add(orderIdField);

        // Boton de busqueda
        searchButton = new JButton("Search");
        searchPanel.add(searchButton);

        add(searchPanel, BorderLayout.WEST);

        // Area donde mostrar los resultados
        resultArea = new JTextArea(12, 35);
        resultArea.setEditable(false);

        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        // Configuracion de la ventana
        setSize(800, 280);
        setLocationRelativeTo(null);
    }

    // Devuelve el ID escrito por el usuario
    public String getOrderId() {
        return orderIdField.getText().trim();
    }

    // Conecta el boton con el controlador
    public void addSearchListener(ActionListener listener) {
        searchButton.addActionListener(listener);
        orderIdField.addActionListener(listener);
    }

    // Muestra el resultado en pantalla
    public void showOrder(String message) {
        resultArea.setText(message);
        resultArea.setCaretPosition(0);
    }
}
