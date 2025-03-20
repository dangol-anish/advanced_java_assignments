package javaassignment2;

import javaassignment2.gui.LoginFrame;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Set Look and Feel for a modern UI
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Run the login window
        SwingUtilities.invokeLater(() -> new LoginFrame());
    }
}
