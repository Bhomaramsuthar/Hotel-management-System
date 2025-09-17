package HotelManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class extraFees extends JFrame {

    Color c1 = new Color(214, 143, 130);
    Color c2 = new Color(110, 76, 74);
    Color c3 = new Color(53, 28, 28);

    JComboBox<String> roomNumberComboBox, feeTypeComboBox;
    JTextField feeAmountField;
    JTextArea feeDescriptionArea;
    JButton saveButton, backButton;

    public extraFees() {
        super("Add Extra Fees");

        Font pixelFont;
        Font poppins = new Font("poppins", Font.BOLD, 18);
        try {
            pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/font/PressStart2P-Regular.ttf"))
                    .deriveFont(16f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(pixelFont);
        } catch (Exception e) {
            e.printStackTrace();
            pixelFont = new Font("Serif", Font.PLAIN, 14);
        }

        // Title Label
        JLabel titleLabel = new JLabel("Add Extra Fees");
        titleLabel.setBounds(350, 30, 400, 40);
        titleLabel.setFont(pixelFont.deriveFont(25f));
        titleLabel.setForeground(c3);
        add(titleLabel);

        // Room Number
        JLabel roomNumberLabel = new JLabel("Room Number:");
        roomNumberLabel.setBounds(100, 100, 250, 30);
        roomNumberLabel.setFont(pixelFont);
        roomNumberLabel.setForeground(c3);
        add(roomNumberLabel);

        roomNumberComboBox = new JComboBox<>();
        roomNumberComboBox.setBounds(350, 100, 400, 30);
        roomNumberComboBox.setBackground(c1);
        roomNumberComboBox.setForeground(c3);
        roomNumberComboBox.setFont(poppins);
        add(roomNumberComboBox);
        populateRoomNumbers();

        // Fee Type
        JLabel feeTypeLabel = new JLabel("Fee Type:");
        feeTypeLabel.setBounds(100, 160, 250, 30);
        feeTypeLabel.setFont(pixelFont);
        feeTypeLabel.setForeground(c3);
        add(feeTypeLabel);

        feeTypeComboBox = new JComboBox<>(new String[]{"Select", "Mini-bar", "Laundry", "Room Service", "Late Check-out", "Damages", "Extra Bed"});
        feeTypeComboBox.setBounds(350, 160, 400, 30);
        feeTypeComboBox.setBackground(c1);
        feeTypeComboBox.setForeground(c3);
        feeTypeComboBox.setFont(poppins);
        add(feeTypeComboBox);

        // Fee Description
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(100, 220, 250, 30);
        descriptionLabel.setFont(pixelFont);
        descriptionLabel.setForeground(c3);
        add(descriptionLabel);

        feeDescriptionArea = new JTextArea();
        feeDescriptionArea.setBounds(350, 220, 400, 100);
        feeDescriptionArea.setFont(poppins);
        feeDescriptionArea.setBackground(c1);
        feeDescriptionArea.setForeground(c3);
        add(feeDescriptionArea);

        // Fee Amount
        JLabel amountLabel = new JLabel("Fee Amount:");
        amountLabel.setBounds(100, 340, 250, 30);
        amountLabel.setFont(pixelFont);
        amountLabel.setForeground(c3);
        add(amountLabel);

        feeAmountField = new JTextField();
        feeAmountField.setBounds(350, 340, 400, 30);
        feeAmountField.setFont(poppins);
        feeAmountField.setBackground(c1);
        feeAmountField.setForeground(c3);
        add(feeAmountField);

        // Save Button
        saveButton = new JButton("Save Fee");
        saveButton.setBounds(150, 420, 180, 40);
        saveButton.setBackground(c1);
        saveButton.setFont(pixelFont);
        saveButton.setForeground(c3);
        add(saveButton);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveExtraFee();
            }
        });

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(550, 420, 180, 40);
        backButton.setBackground(c1);
        backButton.setFont(pixelFont);
        backButton.setForeground(c3);
        add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Background and Frame properties
        setUndecorated(true);
        setLayout(null);
        getContentPane().setBackground(c2);
        setLocation(350, 100);
        setSize(1090, 665);
        setVisible(true);
    }

    private void populateRoomNumbers() {
        try {
            connects c = new connects();
            String query = "SELECT DISTINCT allocated_room FROM customer";
            ResultSet rs = c.statement.executeQuery(query);
            roomNumberComboBox.addItem("Select");
            while (rs.next()) {
                roomNumberComboBox.addItem(rs.getString("allocated_room"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading rooms: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveExtraFee() {
        String roomNumber = (String) roomNumberComboBox.getSelectedItem();
        String feeType = (String) feeTypeComboBox.getSelectedItem();
        String feeDescription = feeDescriptionArea.getText().trim();
        String feeAmount = feeAmountField.getText().trim();

        if (roomNumber == null || roomNumber.isEmpty() || roomNumber.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select a Room Number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (feeType == null || feeType.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select a Fee Type.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (feeDescription.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a fee description.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (feeAmount.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a fee amount.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            connects c = new connects();
            // 1. Get the customer_id_no for the selected room
            String customerIdQuery = "SELECT id_no FROM customer WHERE allocated_room = '" + roomNumber + "'";
            ResultSet rs = c.statement.executeQuery(customerIdQuery);
            String customerId = null;
            if (rs.next()) {
                customerId = rs.getString("id_no");
            } else {
                JOptionPane.showMessageDialog(this, "No customer found for this room number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 2. Add a new record to the extra_fees table
            String insertFeeQuery = "INSERT INTO extra_fees (customer_id_number, room_number, fee_type, description, amount, date_added) VALUES ('" + customerId + "', '" + roomNumber + "', '" + feeType + "', '" + feeDescription + "', '" + feeAmount + "', NOW())";
            c.statement.executeUpdate(insertFeeQuery);

            // 3. Update the customer's total deposit by adding the new fee
            String currentDepositQuery = "SELECT deposite FROM customer WHERE id_no = '" + customerId + "'";
            ResultSet rsDeposit = c.statement.executeQuery(currentDepositQuery);

            if (rsDeposit.next()) {
                int currentDeposit = Integer.parseInt(rsDeposit.getString("deposite"));
                int newFee = Integer.parseInt(feeAmount);
                int updatedDeposit = currentDeposit + newFee;

                String updateDepositQuery = "UPDATE customer SET deposite = '" + updatedDeposit + "' WHERE id_no = '" + customerId + "'";
                c.statement.executeUpdate(updateDepositQuery);
            }

            JOptionPane.showMessageDialog(this, "Extra fee of " + feeAmount + " added for Room " + roomNumber + ".", "Success", JOptionPane.INFORMATION_MESSAGE);

            clearFields();
        } catch (NumberFormatException | SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Invalid amount or database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        if (roomNumberComboBox.getItemCount() > 0) {
            roomNumberComboBox.setSelectedIndex(0);
        }
        feeTypeComboBox.setSelectedIndex(0);
        feeDescriptionArea.setText("");
        feeAmountField.setText("");
    }

    public static void main(String[] args) {
        new extraFees();
    }
}