/*
Kristopher Kuenning
10/10/2025
CSD420
Module 12 Redo Assignment (Module 10 Programming Assignment)
 */

// File: Fan.java
// Requires: MySQL JDBC driver (mysql-connector-j) on classpath.
// Compile: javac -cp .;mysql-connector-j-9.0.0.jar Fan.java
// Run:     java  -cp .;mysql-connector-j-9.0.0.jar Fan

//Write a program that views and updates fan information stored in database titled "databasedb", user ID titled “student1” with a password “pass”.

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class Fan extends JFrame {
    private static final String DB_URL  = "jdbc:mysql://localhost:3306/databasedb?";
    private static final String DB_USER = "student1";
    private static final String DB_PASS = "pass";

    //The table name is to be “fans” with the fields of ID (integer, PRIMARY KEY), firstname (varchar (25)), lastname (varchar(25)), and favoriteteam (varchar(25)).

    private final JTextField idField          = new JTextField(10);
    private final JTextField firstNameField   = new JTextField(20);
    private final JTextField lastNameField    = new JTextField(20);
    private final JTextField favoriteTeamField= new JTextField(20);

    private Connection conn;

    public Fan() {
        super("Fans — Display / Update");

        // Connect once on startup
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (SQLException ex) {
            showError("Database connection failed:\n" + ex.getMessage());
            System.exit(1);
        }

        JPanel buttons = getJPanel();

        setLayout(new BorderLayout(10,10));
        add(firstNameField, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private JPanel getJPanel() {
        JButton displayBtn = new JButton(new AbstractAction("Display") {
            @Override public void actionPerformed(ActionEvent e) { onDisplay(); }
        });
        JButton updateBtn  = new JButton(new AbstractAction("Update") {
            @Override public void actionPerformed(ActionEvent e) { onUpdate(); }
        });

        //Your interface is to have 2 buttons to display and update records.
        //The display button will display the record with the provided ID in the display (ID user provides).
        //The update button will update the record in the database with the changes made in the display.

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttons.add(displayBtn);
        buttons.add(updateBtn);
        return buttons;
    }

    private void onDisplay() {
        Integer id = parseId();
        if (id == null) return;

        String sql = "SELECT firstname, lastname, favoriteteam FROM fans WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    firstNameField.setText(rs.getString("firstname"));
                    lastNameField.setText(rs.getString("lastname"));
                    favoriteTeamField.setText(rs.getString("favoriteteam"));
                    showInfo("Loaded record for ID " + id + ".");
                } else {
                    clearNonIdFields();
                    showInfo("No record found for ID " + id + ".");
                }
            }
        } catch (SQLException ex) {
            showError("Display failed:\n" + ex.getMessage());
        }
    }

    private void onUpdate() {
        Integer id = parseId();
        if (id == null) return;

        String first = firstNameField.getText().trim();
        String last  = lastNameField.getText().trim();
        String team  = favoriteTeamField.getText().trim();

        if (first.length() > 25 || last.length() > 25 || team.length() > 25) {
            showError("Each text field must be ≤ 25 characters.");
            return;
        }

        String sql = "UPDATE fans SET firstname = ?, lastname = ?, favoriteteam = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, first);
            ps.setString(2, last);
            ps.setString(3, team);
            ps.setInt(4, id);
            int updated = ps.executeUpdate();
            if (updated == 0) {
                showInfo("No existing row with ID " + id + ". Nothing updated.");
            } else {
                showInfo("Updated record for ID " + id + ".");
            }
        } catch (SQLException ex) {
            showError("Update failed:\n" + ex.getMessage());
        }
    }

    private Integer parseId() {
        String raw = idField.getText().trim();
        if (raw.isEmpty()) {
            showError("Please enter an ID.");
            return null;
        }
        try {
            return Integer.parseInt(raw);
        } catch (NumberFormatException nfe) {
            showError("ID must be an integer.");
            return null;
        }
    }

    private void clearNonIdFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        favoriteTeamField.setText("");
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
    private void showInfo(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Fan().setVisible(true));
    }
}
