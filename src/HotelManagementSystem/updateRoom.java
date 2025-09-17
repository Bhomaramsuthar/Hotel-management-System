package HotelManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

public class updateRoom extends JFrame {

    Color c1 = new Color(214, 143, 130);
    Color c2 = new Color(110, 76, 74);
    Color c3 = new Color(53, 28, 28);

    JTextField roomNumberField, priceField;
    JComboBox<String> bedTypeComboBox, cleaningStatusComboBox, availabilityComboBox;
    JButton updateButton, backButton;

    public updateRoom() {
        super("Update Room Page");

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
        JLabel titleLabel = new JLabel("Update Room Details");
        titleLabel.setBounds(350, 30, 550, 40);
        titleLabel.setFont(pixelFont.deriveFont(25f));
        titleLabel.setForeground(c3);
        add(titleLabel);

        // Room Number
        JLabel roomNumberLabel = new JLabel("Room Number:");
        roomNumberLabel.setBounds(100, 100, 250, 30);
        roomNumberLabel.setFont(pixelFont);
        roomNumberLabel.setForeground(c3);
        add(roomNumberLabel);

        roomNumberField = new JTextField();
        roomNumberField.setBounds(350, 100, 150, 30);
        roomNumberField.setFont(poppins);
        roomNumberField.setBackground(c1);
        roomNumberField.setForeground(c3);
        add(roomNumberField);
        roomNumberField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchRoom();
            }
        });

        // Bed Type
        JLabel bedTypeLabel = new JLabel("Bed Type:");
        bedTypeLabel.setBounds(100, 160, 250, 30);
        bedTypeLabel.setFont(pixelFont);
        bedTypeLabel.setForeground(c3);
        add(bedTypeLabel);

        bedTypeComboBox = new JComboBox<>(new String[]{"", "Single Bed", "Double Bed"});
        bedTypeComboBox.setBounds(350, 160, 200, 30);
        bedTypeComboBox.setBackground(c1);
        bedTypeComboBox.setForeground(c3);
        bedTypeComboBox.setFont(pixelFont.deriveFont(14f));
        add(bedTypeComboBox);

        // Cleaning Status
        JLabel cleaningStatusLabel = new JLabel("Cleaning Status:");
        cleaningStatusLabel.setBounds(100, 220, 250, 30);
        cleaningStatusLabel.setFont(pixelFont);
        cleaningStatusLabel.setForeground(c3);
        add(cleaningStatusLabel);

        cleaningStatusComboBox = new JComboBox<>(new String[]{"", "Cleaned", "Dirty"});
        cleaningStatusComboBox.setBounds(350, 220, 200, 30);
        cleaningStatusComboBox.setBackground(c1);
        cleaningStatusComboBox.setForeground(c3);
        cleaningStatusComboBox.setFont(pixelFont.deriveFont(14f));
        add(cleaningStatusComboBox);

        // Availability
        JLabel availabilityLabel = new JLabel("Availability:");
        availabilityLabel.setBounds(100, 280, 250, 30);
        availabilityLabel.setFont(pixelFont);
        availabilityLabel.setForeground(c3);
        add(availabilityLabel);

        availabilityComboBox = new JComboBox<>(new String[]{"", "Occupied", "Unoccupied"});
        availabilityComboBox.setBounds(350, 280, 200, 30);
        availabilityComboBox.setBackground(c1);
        availabilityComboBox.setForeground(c3);
        availabilityComboBox.setFont(pixelFont.deriveFont(14f));
        add(availabilityComboBox);

        // Price
        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(100, 340, 250, 30);
        priceLabel.setFont(pixelFont);
        priceLabel.setForeground(c3);
        add(priceLabel);

        priceField = new JTextField();
        priceField.setBounds(350, 340, 200, 30);
        priceField.setFont(poppins);
        priceField.setBackground(c1);
        priceField.setForeground(c3);
        add(priceField);

        // Update Button
        updateButton = new JButton("Update");
        updateButton.setBounds(150, 420, 180, 40);
        updateButton.setBackground(c1);
        updateButton.setFont(pixelFont);
        updateButton.setForeground(c3);
        add(updateButton);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRoomDetails();
            }
        });

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(350, 420, 180, 40);
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

    private void searchRoom() {
        String roomNumber = roomNumberField.getText().trim();
        if (roomNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Room Number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            connects c = new connects();
            String query = "SELECT bed_type, cleaning_status, availability, price FROM room WHERE room_number = '" + roomNumber + "'";
            ResultSet rs = c.statement.executeQuery(query);

            if (rs.next()) {
                // Pre-populate fields with existing data
                bedTypeComboBox.setSelectedItem(rs.getString("bed_type"));
                cleaningStatusComboBox.setSelectedItem(rs.getString("cleaning_status"));
                availabilityComboBox.setSelectedItem(rs.getString("availability"));
                priceField.setText(rs.getString("price"));
            } else {
                JOptionPane.showMessageDialog(this, "Room not found.", "Not Found", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateRoomDetails() {
        String roomNumber = roomNumberField.getText().trim();
        String bedType = (String) bedTypeComboBox.getSelectedItem();
        String cleaningStatus = (String) cleaningStatusComboBox.getSelectedItem();
        String availability = (String) availabilityComboBox.getSelectedItem();
        String price = priceField.getText().trim();

        if (roomNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Room Number and search for a room first.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            connects c = new connects();

            // First, get the current values from the database
            String selectQuery = "SELECT bed_type, cleaning_status, availability, price FROM room WHERE room_number = '" + roomNumber + "'";
            ResultSet rs = c.statement.executeQuery(selectQuery);
            String currentBedType = "";
            String currentCleaningStatus = "";
            String currentAvailability = "";
            String currentPrice = "";

            if (rs.next()) {
                currentBedType = rs.getString("bed_type");
                currentCleaningStatus = rs.getString("cleaning_status");
                currentAvailability = rs.getString("availability");
                currentPrice = rs.getString("price");
            } else {
                JOptionPane.showMessageDialog(this, "Room not found.", "Failed", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Construct the update query dynamically
            StringBuilder updateQueryBuilder = new StringBuilder("UPDATE room SET ");
            boolean firstUpdate = true;

            if (bedType != null && !bedType.isEmpty()) {
                updateQueryBuilder.append("bed_type = '").append(bedType).append("'");
                firstUpdate = false;
            }
            if (cleaningStatus != null && !cleaningStatus.isEmpty()) {
                if (!firstUpdate) updateQueryBuilder.append(", ");
                updateQueryBuilder.append("cleaning_status = '").append(cleaningStatus).append("'");
                firstUpdate = false;
            }
            if (availability != null && !availability.isEmpty()) {
                if (!firstUpdate) updateQueryBuilder.append(", ");
                updateQueryBuilder.append("availability = '").append(availability).append("'");
                firstUpdate = false;
            }
            if (!price.isEmpty()) {
                if (!firstUpdate) updateQueryBuilder.append(", ");
                updateQueryBuilder.append("price = '").append(price).append("'");
                firstUpdate = false;
            }

            if (firstUpdate) {
                JOptionPane.showMessageDialog(this, "No changes to update.", "No Changes", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            updateQueryBuilder.append(" WHERE room_number = '").append(roomNumber).append("'");

            int rowsAffected = c.statement.executeUpdate(updateQueryBuilder.toString());

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Room details updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Update failed. Room not found or no changes made.", "Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        roomNumberField.setText("");
        priceField.setText("");
        bedTypeComboBox.setSelectedIndex(0);
        cleaningStatusComboBox.setSelectedIndex(0);
        availabilityComboBox.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        new updateRoom();
    }
}