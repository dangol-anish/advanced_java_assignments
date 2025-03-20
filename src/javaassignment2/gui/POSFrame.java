package javaassignment2.gui;
import javaassignment2.dao.ProductDAO;
import javaassignment2.models.Product;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class POSFrame extends JFrame {
    private JTable productTable;
    private JButton addToCartButton, checkoutButton;
    private JLabel totalLabel;
    private DefaultListModel<String> cartModel;
    private JList<String> cartList;
    private ArrayList<Product> cartItems;

    public POSFrame() {
        setTitle("Point of Sale System");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Product List
        productTable = new JTable();
        loadProducts();
        add(new JScrollPane(productTable), BorderLayout.CENTER);

        // Cart Section
        cartModel = new DefaultListModel<>();
        cartList = new JList<>(cartModel);
        cartItems = new ArrayList<>();
        add(new JScrollPane(cartList), BorderLayout.EAST);

        // Bottom Panel (Checkout)
        JPanel bottomPanel = new JPanel();
        totalLabel = new JLabel("Total: $0.00");
        bottomPanel.add(totalLabel);

        addToCartButton = new JButton("Add to Cart");
        checkoutButton = new JButton("Checkout");

        bottomPanel.add(addToCartButton);
        bottomPanel.add(checkoutButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // Add to Cart Action
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = productTable.getSelectedRow();
                if (selectedRow >= 0) {
                    ProductDAO dao = new ProductDAO();
                    ArrayList<Product> products = dao.getProducts();
                    Product selectedProduct = products.get(selectedRow);
                    cartItems.add(selectedProduct);
                    cartModel.addElement(selectedProduct.getName() + " - $" + selectedProduct.getPrice());
                    updateTotal();
                }
            }
        });

        // Checkout Action
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CheckoutPanel(cartItems, POSFrame.this);
            }
        });

        setLocationRelativeTo(null);
    }

    private void loadProducts() {
        ProductDAO dao = new ProductDAO();
        ArrayList<Product> products = dao.getProducts();
        String[][] data = new String[products.size()][3];

        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            data[i] = new String[]{p.getName(), p.getCategory(), "$" + p.getPrice()};
        }

        productTable.setModel(new javax.swing.table.DefaultTableModel(
            data, new String[]{"Name", "Category", "Price"}
        ));
    }

    private void updateTotal() {
        double total = 0;
        for (Product p : cartItems) {
            total += p.getPrice();
        }
        totalLabel.setText("Total: $" + total);
    }
}
