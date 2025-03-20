package javaassignment2.dao;
import javaassignment2.models.Sale;
import javaassignment2.models.SaleItem;
import java.sql.*;
import javaassignment2.DatabaseConnection;
import java.util.List;

public class SalesDAO {
    public int recordSale(Sale sale) {
        String sql = "INSERT INTO sales (total_amount, payment_method) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDouble(1, sale.getTotalAmount());
            stmt.setString(2, sale.getPaymentMethod());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    // Fix Missing Method: recordSaleItems
    public void recordSaleItems(int saleId, List<SaleItem> saleItems) {
        String sql = "INSERT INTO sales_items (sale_id, product_id, quantity, subtotal) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (SaleItem item : saleItems) {
                stmt.setInt(1, saleId);
                stmt.setInt(2, item.getProduct().getId());
                stmt.setInt(3, item.getQuantity());
                stmt.setDouble(4, item.getSubtotal());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
