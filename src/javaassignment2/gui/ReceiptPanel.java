package javaassignment2.gui;
import javaassignment2.models.Sale;
import javax.swing.*;

public class ReceiptPanel extends JFrame {
    public ReceiptPanel(Sale sale) {
        setTitle("Receipt");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea receiptArea = new JTextArea();
        receiptArea.setText("SALE RECEIPT\n");
        receiptArea.append("Total: $" + sale.getTotalAmount() + "\n");
        receiptArea.append("Payment Method: " + sale.getPaymentMethod() + "\n");

        add(new JScrollPane(receiptArea));
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
