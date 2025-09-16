package HotelManagementSystem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

public class searchRoom extends JFrame {

    Color c1 = new Color(214, 143, 130);
    Color c2 = new Color(110, 76, 74);
    Color c3 = new Color(53, 28, 28);

    JComboBox<String> roomType, availabilityStatus;
    JTable table;
    JButton searchButton, backButton;

    searchRoom() {
        super("Search Room Page");

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
        JLabel titleLabel = new JLabel("Search Room");
        titleLabel.setBounds(420, 30, 450, 40);
        titleLabel.setFont(pixelFont.deriveFont(25f));
        titleLabel.setForeground(c3);
        add(titleLabel);

        // Room Type Label and ComboBox
        JLabel roomTypeLabel = new JLabel("Room Type");
        roomTypeLabel.setBounds(100, 100, 150, 30);
        roomTypeLabel.setFont(pixelFont);
        roomTypeLabel.setForeground(c3);
        add(roomTypeLabel);

        roomType = new JComboBox<>(new String[]{"All", "Single Bed", "Double Bed"});
        roomType.setBounds(260, 100, 150, 30);
        roomType.setBackground(c1);
        roomTypeLabel.setFont(poppins);
        roomType.setForeground(c3);
        add(roomType);

        // Availability Status Label and ComboBox
        JLabel availabilityLabel = new JLabel("Availability Status");
        availabilityLabel.setBounds(480, 100, 200, 30);
        availabilityLabel.setFont(pixelFont);
        availabilityLabel.setForeground(c3);
        add(availabilityLabel);

        availabilityStatus = new JComboBox<>(new String[]{"All", "Occupied", "Unoccupied"});
        availabilityStatus.setBounds(680, 100, 150, 30);
        availabilityStatus.setBackground(c1);
        availabilityLabel.setFont(poppins);
        availabilityStatus.setForeground(c3);
        add(availabilityStatus);

        // Search Button
        searchButton = new JButton("Search");
        searchButton.setBounds(400, 600, 180, 40);
        searchButton.setBackground(c1);
        searchButton.setFont(pixelFont.deriveFont(14f));
        searchButton.setForeground(c3);
        add(searchButton);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchRooms();
            }
        });

        // Table setup
        table = new JTable();
        table.setFont(poppins.deriveFont(14f));
        table.setForeground(c3);
        table.setBackground(c1);
        table.setRowHeight(35);
        table.setGridColor(c3);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(100, 180, 900, 400);
        add(scrollPane);

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(100, 600, 180, 40);
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

        // Initial table load
        try {
            searchRooms(); // Load all rooms on startup
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Frame properties
        setUndecorated(true);
        setLayout(null);
        getContentPane().setBackground(c2);
        setLocation(350, 100);
        setSize(1090, 665);
        setVisible(true);
    }

    private void searchRooms() {
        try {
            connects c = new connects();
            String roomTypeQuery = (String) roomType.getSelectedItem();
            String availabilityQuery = (String) availabilityStatus.getSelectedItem();

            StringBuilder queryBuilder = new StringBuilder("SELECT room_number, bed_type, cleaning_status, availability, price FROM room WHERE 1=1");

            if (!roomTypeQuery.equals("All")) {
                queryBuilder.append(" AND bed_type = '").append(roomTypeQuery).append("'");
            }

            if (!availabilityQuery.equals("All")) {
                queryBuilder.append(" AND availability = '").append(availabilityQuery).append("'");
            }

            ResultSet resultSet = c.statement.executeQuery(queryBuilder.toString());
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error retrieving room data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new searchRoom();
    }
}