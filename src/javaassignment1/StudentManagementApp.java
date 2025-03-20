import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class StudentManagementApp extends JFrame {
    private JTextField nameField, emailField, ageField, gradeField, searchField;
    private JTable table;
    private DefaultTableModel model;
    private StudentDAO studentDAO;

    public StudentManagementApp() {
        studentDAO = new StudentDAO();

        setTitle("Student Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input Panel
        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Age:"));
        ageField = new JTextField();
        panel.add(ageField);

        panel.add(new JLabel("Grade:"));
        gradeField = new JTextField();
        panel.add(gradeField);

        JButton addButton = new JButton("Add Student");
        panel.add(addButton);

        // Table
        model = new DefaultTableModel(new String[]{"ID", "Name", "Email", "Age", "Grade"}, 0);
        table = new JTable(model);
        loadStudents();
        JScrollPane scrollPane = new JScrollPane(table);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton deleteButton = new JButton("Delete");
        JButton updateButton = new JButton("Update");
        JButton searchButton = new JButton("Search");
        searchField = new JTextField(10);
        buttonPanel.add(new JLabel("Search:"));
        buttonPanel.add(searchField);
        buttonPanel.add(searchButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        // Event Listeners
        addButton.addActionListener(e -> {
            studentDAO.insertStudent(nameField.getText(), emailField.getText(), Integer.parseInt(ageField.getText()), gradeField.getText());
            loadStudents();
        });

        deleteButton.addActionListener(e -> {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter ID to delete:"));
            studentDAO.deleteStudent(id);
            loadStudents();
        });
        
        
        updateButton.addActionListener(e -> {
    int selectedRow = table.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Select a student to update.");
        return;
    }

    int id = (int) model.getValueAt(selectedRow, 0);
    String updatedName = nameField.getText().trim();
    String updatedEmail = emailField.getText().trim();
    String updatedGrade = gradeField.getText().trim();
    
    // Debugging: Print age input
    System.out.println("Age Input: [" + ageField.getText() + "]");

    if (ageField.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Age cannot be empty.");
        return;
    }

    int updatedAge;
    try {
        updatedAge = Integer.parseInt(ageField.getText().trim());
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Age must be a valid number.");
        return;
    }

    boolean success = studentDAO.updateStudent(id, updatedName, updatedEmail, updatedAge, updatedGrade);
    if (success) {
        JOptionPane.showMessageDialog(this, "Student updated successfully!");
        loadStudents();
    } else {
        JOptionPane.showMessageDialog(this, "Failed to update student.");
    }
});

        
        searchButton.addActionListener(e -> loadStudents(studentDAO.searchStudent(searchField.getText())));

        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void loadStudents() {
        loadStudents(studentDAO.getAllStudents());
    }

    private void loadStudents(List<Student> students) {
        model.setRowCount(0);
        for (Student s : students) {
            model.addRow(new Object[]{s.getId(), s.getName(), s.getEmail(), s.getAge(), s.getGrade()});
        }
    }

    public static void main(String[] args) {
        new StudentManagementApp();
    }
}
