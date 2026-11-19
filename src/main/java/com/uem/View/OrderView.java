package com.uem.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;

public class OrderView extends JFrame {
    private final JTextField orderIdField;
    private final JButton searchButton;
    private final JTextArea resultArea;

    public OrderView() {
        setTitle("Order Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Se carga desde resources, por lo que funciona dentro del JAR.
        URL iconUrl = OrderView.class.getResource("/app.png");
        if (iconUrl != null) {
            setIconImage(new ImageIcon(iconUrl).getImage());
        }

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(new JLabel("Order ID:"));
        orderIdField = new JTextField(10);
        searchPanel.add(orderIdField);
        searchButton = new JButton("Search");
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.WEST);

        resultArea = new JTextArea(12, 35);
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        setSize(800, 280);
        setLocationRelativeTo(null);
    }

    public String getOrderId() {
        return orderIdField.getText().trim();
    }

    public void addSearchListener(ActionListener listener) {
        searchButton.addActionListener(listener);
        orderIdField.addActionListener(listener);
    }

    public void showOrder(String message) {
        resultArea.setText(message);
        resultArea.setCaretPosition(0);
    }
}
