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

public class updateCustomer extends JFrame {

    Color c1 = new Color(214, 143, 130);
    Color c2 = new Color(110, 76, 74);
    Color c3 = new Color(53, 28, 28);

    JTextField jtName, jtIdNo, jtCountry, jtDepo;
    JComboBox<String> jcId, jcGender, jcRoom;
    JButton updateButton, backButton;

    public updateCustomer() {
        super("Update Customer Page");

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
        JLabel titleLabel = new JLabel("Update Customer Details");
        titleLabel.setBounds(350, 30, 650, 40);
        titleLabel.setFont(pixelFont.deriveFont(25f));
        titleLabel.setForeground(c3);
        add(titleLabel);

        // ID Number
        JLabel idNoLabel = new JLabel("ID Number:");
        idNoLabel.setBounds(100, 100, 250, 30);
        idNoLabel.setFont(pixelFont);
        idNoLabel.setForeground(c3);
        add(idNoLabel);

        jtIdNo = new JTextField();
        jtIdNo.setBounds(350, 100, 200, 30);
        jtIdNo.setFont(poppins);
        jtIdNo.setBackground(c1);
        jtIdNo.setForeground(c3);
        add(jtIdNo);
        jtIdNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchCustomer();
            }
        });

        // Name
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(100, 160, 250, 30);
        nameLabel.setFont(pixelFont);
        nameLabel.setForeground(c3);
        add(nameLabel);

        jtName = new JTextField();
        jtName.setBounds(350, 160, 200, 30);
        jtName.setFont(poppins);
        jtName.setBackground(c1);
        jtName.setForeground(c3);
        add(jtName);

        // ID Type
        JLabel idTypeLabel = new JLabel("ID Type:");
        idTypeLabel.setBounds(100, 220, 250, 30);
        idTypeLabel.setFont(pixelFont);
        idTypeLabel.setForeground(c3);
        add(idTypeLabel);

        jcId = new JComboBox<>(new String[]{"", "Aadhar", "Passport", "Voter ID", "Driving License"});
        jcId.setBounds(350, 220, 200, 30);
        jcId.setBackground(c1);
        jcId.setForeground(c3);
        jcId.setFont(pixelFont.deriveFont(14f));
        add(jcId);

        // Gender
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(100, 280, 250, 30);
        genderLabel.setFont(pixelFont);
        genderLabel.setForeground(c3);
        add(genderLabel);

        jcGender = new JComboBox<>(new String[]{"", "Male", "Female"});
        jcGender.setBounds(350, 280, 200, 30);
        jcGender.setBackground(c1);
        jcGender.setForeground(c3);
        jcGender.setFont(pixelFont.deriveFont(14f));
        add(jcGender);

        // Country
        JLabel countryLabel = new JLabel("Country:");
        countryLabel.setBounds(100, 340, 250, 30);
        countryLabel.setFont(pixelFont);
        countryLabel.setForeground(c3);
        add(countryLabel);

        jtCountry = new JTextField();
        jtCountry.setBounds(350, 340, 200, 30);
        jtCountry.setFont(poppins);
        jtCountry.setBackground(c1);
        jtCountry.setForeground(c3);
        add(jtCountry);

        // Allocated Room
        JLabel roomLabel = new JLabel("Allocated Room:");
        roomLabel.setBounds(100, 400, 250, 30);
        roomLabel.setFont(pixelFont);
        roomLabel.setForeground(c3);
        add(roomLabel);

        jcRoom = new JComboBox<>();
        jcRoom.setBounds(350, 400, 200, 30);
        jcRoom.setBackground(c1);
        jcRoom.setForeground(c3);
        jcRoom.setFont(pixelFont.deriveFont(14f));
        add(jcRoom);
        populateRoomComboBox();

        // Check-in Date (non-editable for update)
        JLabel checkInLabel = new JLabel("Check-in Date:");
        checkInLabel.setBounds(100, 460, 250, 30);
        checkInLabel.setFont(pixelFont);
        checkInLabel.setForeground(c3);
        add(checkInLabel);

        JLabel checkInDateLabel = new JLabel("Date Not Found");
        checkInDateLabel.setBounds(350, 460, 250, 30);
        checkInDateLabel.setFont(poppins);
        checkInDateLabel.setForeground(c3);
        add(checkInDateLabel);

        // Deposit
        JLabel depositLabel = new JLabel("Deposit:");
        depositLabel.setBounds(100, 520, 250, 30);
        depositLabel.setFont(pixelFont);
        depositLabel.setForeground(c3);
        add(depositLabel);

        jtDepo = new JTextField();
        jtDepo.setBounds(350, 520, 200, 30);
        jtDepo.setFont(poppins);
        jtDepo.setBackground(c1);
        jtDepo.setForeground(c3);
        add(jtDepo);

        // Update Button
        updateButton = new JButton("Update");
        updateButton.setBounds(150, 580, 180, 40);
        updateButton.setBackground(c1);
        updateButton.setFont(pixelFont);
        updateButton.setForeground(c3);
        add(updateButton);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCustomerDetails();
            }
        });

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(350, 580, 180, 40);
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

    // Method to populate the room combo box with available rooms
    private void populateRoomComboBox() {
        try {
            connects c = new connects();
            ResultSet rs = c.statement.executeQuery("SELECT room_number FROM room WHERE availability = 'Unoccupied'");
            jcRoom.addItem(""); // Add an empty option for no change
            while (rs.next()) {
                jcRoom.addItem(rs.getString("room_number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading rooms: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchCustomer() {
        String idNumber = jtIdNo.getText().trim();
        if (idNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Customer ID Number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            connects c = new connects();
            String query = "SELECT name, id_type, gender, country, allocated_room, check_in, deposite FROM customer WHERE id_no = '" + idNumber + "'";
            ResultSet rs = c.statement.executeQuery(query);

            if (rs.next()) {
                // Pre-populate fields with existing data
                jtName.setText(rs.getString("name"));
                jcId.setSelectedItem(rs.getString("id_type"));
                jcGender.setSelectedItem(rs.getString("gender"));
                jtCountry.setText(rs.getString("country"));
                jcRoom.setSelectedItem(rs.getString("allocated_room"));
                // The check-in date is a label, no need to update it
                jtDepo.setText(rs.getString("deposite"));
            } else {
                JOptionPane.showMessageDialog(this, "Customer not found.", "Not Found", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateCustomerDetails() {
        String idNumber = jtIdNo.getText().trim();
        if (idNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Customer ID Number and search first.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            connects c = new connects();

            // Get current room number before update
            String currentRoomQuery = "SELECT allocated_room FROM customer WHERE id_no = '" + idNumber + "'";
            ResultSet rsCurrentRoom = c.statement.executeQuery(currentRoomQuery);
            String currentRoom = "";
            if(rsCurrentRoom.next()){
                currentRoom = rsCurrentRoom.getString("allocated_room");
            }

            // Construct the update query dynamically
            StringBuilder updateQueryBuilder = new StringBuilder("UPDATE customer SET ");
            boolean firstUpdate = true;

            String name = jtName.getText().trim();
            if (!name.isEmpty()) {
                updateQueryBuilder.append("name = '").append(name).append("'");
                firstUpdate = false;
            }

            String idType = (String) jcId.getSelectedItem();
            if (idType != null && !idType.isEmpty()) {
                if (!firstUpdate) updateQueryBuilder.append(", ");
                updateQueryBuilder.append("id_type = '").append(idType).append("'");
                firstUpdate = false;
            }

            String gender = (String) jcGender.getSelectedItem();
            if (gender != null && !gender.isEmpty()) {
                if (!firstUpdate) updateQueryBuilder.append(", ");
                updateQueryBuilder.append("gender = '").append(gender).append("'");
                firstUpdate = false;
            }

            String country = jtCountry.getText().trim();
            if (!country.isEmpty()) {
                if (!firstUpdate) updateQueryBuilder.append(", ");
                updateQueryBuilder.append("country = '").append(country).append("'");
                firstUpdate = false;
            }

            String newRoom = (String) jcRoom.getSelectedItem();
            if (newRoom != null && !newRoom.isEmpty() && !newRoom.equals(currentRoom)) {
                if (!firstUpdate) updateQueryBuilder.append(", ");
                updateQueryBuilder.append("allocated_room = '").append(newRoom).append("'");
                firstUpdate = false;

                // If room is changed, update old room to Unoccupied and new room to Occupied
                String updateOldRoomQuery = "UPDATE room SET availability = 'Unoccupied' WHERE room_number = '" + currentRoom + "'";
                c.statement.executeUpdate(updateOldRoomQuery);

                String updateNewRoomQuery = "UPDATE room SET availability = 'Occupied' WHERE room_number = '" + newRoom + "'";
                c.statement.executeUpdate(updateNewRoomQuery);
            }

            String deposit = jtDepo.getText().trim();
            if (!deposit.isEmpty()) {
                if (!firstUpdate) updateQueryBuilder.append(", ");
                updateQueryBuilder.append("deposite = '").append(deposit).append("'");
                firstUpdate = false;
            }

            if (firstUpdate) {
                JOptionPane.showMessageDialog(this, "No changes to update.", "No Changes", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            updateQueryBuilder.append(" WHERE id_no = '").append(idNumber).append("'");

            int rowsAffected = c.statement.executeUpdate(updateQueryBuilder.toString());

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Customer details updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Update failed. Customer not found or no changes made.", "Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        jtIdNo.setText("");
        jtName.setText("");
        jtCountry.setText("");
        jtDepo.setText("");
        jcId.setSelectedIndex(0);
        jcGender.setSelectedIndex(0);
        jcRoom.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        new updateCustomer();
    }
}