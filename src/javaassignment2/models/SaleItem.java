package javaassignment2.models;

public class SaleItem {
    private int saleId;
    private Product product;
    private int quantity;
    private double subtotal;

    public SaleItem(int saleId, Product product, int quantity) {
        this.saleId = saleId;
        this.product = product;
        this.quantity = quantity;
        this.subtotal = product.getPrice() * quantity;
    }

    // Getter Methods
    public int getSaleId() {
        return saleId;
    }

    public Product getProduct() { // Fix for recordSaleItems()
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }
}
