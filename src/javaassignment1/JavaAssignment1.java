import java.sql.*;
import java.util.Scanner;

public class JavaAssignment1 {
    // JDBC URL, username, and password of MySQL server
    private static final String URL = "jdbc:mysql://localhost:3306/StudentDB";
    private static final String USER = "root";  
    private static final String PASSWORD = "@nisH###11";  

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            // Load and register MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            while (true) {
                System.out.println("\nStudent Database System");
                System.out.println("1. Insert Student");
                System.out.println("2. View Students");
                System.out.println("3. Update Student");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        insertStudent(connection, scanner);
                        break;
                    case 2:
                        viewStudents(connection);
                        break;
                    case 3:
                        updateStudent(connection, scanner);
                        break;
                    case 4:
                        deleteStudent(connection, scanner);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        connection.close();
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

private static void insertStudent(Connection connection, Scanner scanner) throws SQLException {
    System.out.print("Enter name: ");
    scanner.nextLine();  // Consume the leftover newline
    String name = scanner.nextLine();
    
    System.out.print("Enter age: ");
    while (!scanner.hasNextInt()) {  // Validate input
        System.out.println("Invalid input. Please enter a valid age.");
        scanner.next(); // Consume invalid input
    }
    int age = scanner.nextInt();
    
    System.out.print("Enter grade: ");
    scanner.nextLine();  // Consume leftover newline
    String grade = scanner.nextLine();

    String sql = "INSERT INTO students (name, age, grade) VALUES (?, ?, ?)";
    PreparedStatement stmt = connection.prepareStatement(sql);
    stmt.setString(1, name);
    stmt.setInt(2, age);
    stmt.setString(3, grade);

    int rows = stmt.executeUpdate();
    if (rows > 0) {
        System.out.println("Student added successfully!");
    }
}

    private static void viewStudents(Connection connection) throws SQLException {
        String sql = "SELECT * FROM students";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        System.out.println("\nStudent Records:");
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") +
                               ", Age: " + rs.getInt("age") + ", Grade: " + rs.getString("grade"));
        }
    }

  private static void updateStudent(Connection connection, Scanner scanner) throws SQLException {
    System.out.print("Enter Student ID to update: ");
    
    while (!scanner.hasNextInt()) {  // Validate numeric input for ID
        System.out.println("Invalid input. Please enter a valid Student ID.");
        scanner.next(); // Consume invalid input
    }
    int id = scanner.nextInt();
    scanner.nextLine();  // Consume the leftover newline

    System.out.print("Enter new name: ");
    String name = scanner.nextLine();  // Now properly reads name

    System.out.print("Enter new age: ");
    while (!scanner.hasNextInt()) {  // Validate input for age
        System.out.println("Invalid input. Please enter a valid age.");
        scanner.next(); // Consume invalid input
    }
    int age = scanner.nextInt();
    scanner.nextLine();  // Consume leftover newline

    System.out.print("Enter new grade: ");
    String grade = scanner.nextLine();

    String sql = "UPDATE students SET name=?, age=?, grade=? WHERE id=?";
    PreparedStatement stmt = connection.prepareStatement(sql);
    stmt.setString(1, name);
    stmt.setInt(2, age);
    stmt.setString(3, grade);
    stmt.setInt(4, id);

    int rows = stmt.executeUpdate();
    if (rows > 0) {
        System.out.println("Student updated successfully!");
    } else {
        System.out.println("Student ID not found.");
    }
}

    private static void deleteStudent(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Student ID to delete: ");
        int id = scanner.nextInt();

        String sql = "DELETE FROM students WHERE id=?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);

        int rows = stmt.executeUpdate();
        if (rows > 0) {
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student ID not found.");
        }
    }
}
