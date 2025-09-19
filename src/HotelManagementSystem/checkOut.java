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

public class checkOut extends JFrame {

    Color c1 = new Color(214, 143, 130);
    Color c2 = new Color(110,76,74);
    Color c3 = new Color(53, 28, 28);

    JLabel roomLabel,nameLabel,paidLabel,pendingLabel,inLabel,outLabel,l1;
    JLabel nameValue, paidValue, pendingValue, inValue, outValue; // New labels for displaying values
    JTextField roomText;
    JButton search,checkOut,back;

    checkOut(){
        super("Checkout Page");

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
        l1 = new JLabel("Check Out Details");
        l1.setBounds(300,30,650,60);
        l1.setFont(pixelFont.deriveFont(25f));
        l1.setForeground(c3);
        add(l1);

        // Room No. and Search Button
        roomLabel= new JLabel("Room No.");
        roomLabel.setBounds(120,130,200,30);
        roomLabel.setFont(pixelFont);
        roomLabel.setForeground(c3);
        add(roomLabel);

        roomText = new JTextField();
        roomText.setBounds(400,130,200,30);
        roomText.setBackground(c1);
        roomText.setForeground(c3);
        roomText.setFont(poppins);
        add(roomText);

        search = new JButton("Search");
        search.setBounds(650,130,180,40);
        search.setBackground(c1);
        search.setFont(pixelFont);
        search.setForeground(c3);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchCustomerAndRoom();
            }
        });
        add(search);

        // Name
        nameLabel= new JLabel("Name");
        nameLabel.setBounds(120,180,200,30);
        nameLabel.setFont(pixelFont);
        nameLabel.setForeground(c3);
        add(nameLabel);

        nameValue = new JLabel("");
        nameValue.setBounds(400,180,200,30);
        nameValue.setFont(poppins);
        nameValue.setForeground(c3);
        add(nameValue);

        // Paid Amount
        paidLabel= new JLabel("Paid Amount");
        paidLabel.setBounds(120,230,200,30);
        paidLabel.setFont(pixelFont);
        paidLabel.setForeground(c3);
        add(paidLabel);

        paidValue = new JLabel("");
        paidValue.setBounds(400,230,250,30);
        paidValue.setFont(poppins);
        paidValue.setForeground(c3);
        add(paidValue);

        // Pending Amount
        pendingLabel= new JLabel("Pending Amount");
        pendingLabel.setBounds(120,280,250,30);
        pendingLabel.setFont(pixelFont);
        pendingLabel.setForeground(c3);
        add(pendingLabel);

        pendingValue = new JLabel("");
        pendingValue.setBounds(400,280,200,30);
        pendingValue.setFont(poppins);
        pendingValue.setForeground(c3);
        add(pendingValue);

        // Check-in Time
        inLabel= new JLabel("Check-In Time");
        inLabel.setBounds(120,330,250,30);
        inLabel.setFont(pixelFont);
        inLabel.setForeground(c3);
        add(inLabel);

        inValue = new JLabel("");
        inValue.setBounds(400,330,250,30);
        inValue.setFont(poppins);
        inValue.setForeground(c3);
        add(inValue);

        // Check-out Time
        outLabel= new JLabel("Check-Out Time");
        outLabel.setBounds(120,380,350,30);
        outLabel.setFont(pixelFont);
        outLabel.setForeground(c3);
        add(outLabel);

        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        outValue = new JLabel(sdf.format(now));
        outValue.setBounds(400,380,250,30);
        outValue.setFont(poppins);
        outValue.setForeground(c3);
        add(outValue);

        // Buttons
        checkOut = new JButton("Check Out");
        checkOut.setBounds(100,500,220,40);
        checkOut.setBackground(c1);
        checkOut.setFont(pixelFont);
        checkOut.setForeground(c3);
        checkOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performCheckOut();
            }
        });
        add(checkOut);

        back = new JButton("Back");
        back.setBounds(440,500,180,40);
        back.setBackground(c1);
        back.setFont(pixelFont);
        back.setForeground(c3);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(back);

        // Background
        setUndecorated(true);
        setLayout(null);
        getContentPane().setBackground(c2);
        setLocation(350,100);
        setSize(1090,665);
        setVisible(true);
    }

    private void searchCustomerAndRoom() {
        String roomNumber = roomText.getText().trim();
        if (roomNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a room number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            connects c = new connects();

            // Fetch customer details
            String customerQuery = "SELECT customer_name, deposite, check_in FROM customer WHERE allocated_room = '" + roomNumber + "'";
            ResultSet rsCustomer = c.statement.executeQuery(customerQuery);

            if (rsCustomer.next()) {
                String customerName = rsCustomer.getString("customer_name");
                int depositAmount = Integer.parseInt(rsCustomer.getString("deposite"));
                String checkInTime = rsCustomer.getString("check_in");

                // Fetch room price
                String roomQuery = "SELECT price FROM room WHERE room_number = '" + roomNumber + "'";
                ResultSet rsRoom = c.statement.executeQuery(roomQuery);
                int roomPrice = 0;
                if (rsRoom.next()) {
                    roomPrice = Integer.parseInt(rsRoom.getString("price"));
                }

                // Fetch total extra fees for the customer
                String feesQuery = "SELECT SUM(amount) AS total_fees FROM extra_fees WHERE room_number = '" + roomNumber + "'";
                ResultSet rsFees = c.statement.executeQuery(feesQuery);
                int totalFees = 0;
                if (rsFees.next()) {
                    totalFees = rsFees.getInt("total_fees");
                }

                // Calculate pending amount
                int totalCharges = roomPrice + totalFees;
                int pendingAmount = totalCharges - depositAmount;

                // Update UI labels
                nameValue.setText(customerName);
                paidValue.setText(String.valueOf(depositAmount));
                pendingValue.setText(String.valueOf(pendingAmount));
                inValue.setText(checkInTime);
            } else {
                JOptionPane.showMessageDialog(this, "No customer found for this room number.", "Not Found", JOptionPane.INFORMATION_MESSAGE);
                clearLabels();
            }
        } catch (SQLException | NumberFormatException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching data: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            clearLabels();
        }
    }

    private void performCheckOut() {
        String roomNumber = roomText.getText().trim();
        if (roomNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please search for a room first.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            connects c = new connects();
            String checkOutTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            // Add a new column 'check_out' to the customer table if it doesn't exist
            // This is a one-time operation, but good practice to handle.
            try {
                c.statement.executeUpdate("ALTER TABLE customer ADD check_out VARCHAR(50)");
            } catch (SQLException e) {
                // Column already exists, do nothing
            }

            // Update the check_out time for the customer
            String updateCustomerQuery = "UPDATE customer SET check_out = '" + checkOutTime + "' WHERE allocated_room = '" + roomNumber + "'";
            c.statement.executeUpdate(updateCustomerQuery);

            // Update the room's availability and cleaning status
            String updateRoomQuery = "UPDATE room SET availability = 'Unoccupied', cleaning_status = 'Dirty' WHERE room_number = '" + roomNumber + "'";
            c.statement.executeUpdate(updateRoomQuery);

            JOptionPane.showMessageDialog(this, "Checkout successful for Room " + roomNumber, "Success", JOptionPane.INFORMATION_MESSAGE);
            clearAllFields();

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error during checkout: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearLabels() {
        nameValue.setText("");
        paidValue.setText("");
        pendingValue.setText("");
        inValue.setText("");
    }

    private void clearAllFields() {
        roomText.setText("");
        clearLabels();
    }

    public static void main(String[] args) {
        new checkOut();
    }
}