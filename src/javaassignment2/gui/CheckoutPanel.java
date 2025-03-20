package javaassignment2.gui;
import javaassignment2.dao.SalesDAO;
import javaassignment2.models.Sale;
import javaassignment2.models.SaleItem;
import javaassignment2.models.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class CheckoutPanel extends JFrame {
    private JComboBox<String> paymentMethodBox;
    private JButton confirmButton;
    private JLabel totalLabel;
    private ArrayList<Product> cartItems;
    private POSFrame parentFrame;

    public CheckoutPanel(ArrayList<Product> cartItems, POSFrame parentFrame) {
        this.cartItems = cartItems;
        this.parentFrame = parentFrame;

        setTitle("Checkout");
        setSize(400, 300);
        setLayout(new GridLayout(4, 1));

        totalLabel = new JLabel("Total: $" + calculateTotal());
        add(totalLabel);

        String[] paymentMethods = {"Cash", "Credit Card", "Digital Wallet"};
        paymentMethodBox = new JComboBox<>(paymentMethods);
        add(paymentMethodBox);

        confirmButton = new JButton("Confirm Payment");
        add(confirmButton);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizeSale();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private double calculateTotal() {
        double total = 0;
        for (Product p : cartItems) {
            total += p.getPrice();
        }
        return total >= 100 ? total * 0.9 : total; // 10% discount if total > $100
    }

    private void finalizeSale() {
        SalesDAO salesDAO = new SalesDAO();
        Sale sale = new Sale(0, new Date(), calculateTotal(), paymentMethodBox.getSelectedItem().toString());
        int saleId = salesDAO.recordSale(sale);

        if (saleId != -1) {
            ArrayList<SaleItem> saleItems = new ArrayList<>();
            for (Product p : cartItems) {
                saleItems.add(new SaleItem(saleId, p, 1));
            }
            salesDAO.recordSaleItems(saleId, saleItems);
            new ReceiptPanel(sale);
            dispose();
            parentFrame.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Error processing payment!");
        }
    }
}
