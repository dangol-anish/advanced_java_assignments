package javaassignment2.models;
import java.util.Date;

public class Sale {
    private int id;
    private Date date;
    private double totalAmount;
    private String paymentMethod;

    public Sale(int id, Date date, double totalAmount, String paymentMethod) {
        this.id = id;
        this.date = date;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
    }

    // Getter Methods (Fix Missing Methods)
    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getPaymentMethod() { // This fixes the error in CheckoutPanel & ReceiptPanel
        return paymentMethod;
    }
}
