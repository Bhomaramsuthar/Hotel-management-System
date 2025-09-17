package HotelManagementSystem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

public class searchCustomer extends JFrame {

    Color c1 = new Color(214, 143, 130);
    Color c2 = new Color(110, 76, 74);
    Color c3 = new Color(53, 28, 28);

    JTextField searchField;
    JComboBox<String> idTypeComboBox, genderComboBox;
    JButton searchButton, backButton;
    JTable table;

    public searchCustomer() {
        super("Search Customer Page");

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
        JLabel titleLabel = new JLabel("Search Customer");
        titleLabel.setBounds(420, 30, 550, 40);
        titleLabel.setFont(pixelFont.deriveFont(25f));
        titleLabel.setForeground(c3);
        add(titleLabel);

        // Search Label and TextField
        JLabel searchLabel = new JLabel("Name/ID No");
        searchLabel.setBounds(100, 100, 300, 30);
        searchLabel.setFont(pixelFont);
        searchLabel.setForeground(c3);
        add(searchLabel);

        searchField = new JTextField();
        searchField.setBounds(280, 100, 200, 30);
        searchField.setFont(poppins);
        searchField.setBackground(c1);
        searchField.setForeground(c3);
        add(searchField);

        // ID Type Label and ComboBox
        JLabel idTypeLabel = new JLabel("ID Type");
        idTypeLabel.setBounds(580, 100, 150, 30);
        idTypeLabel.setFont(pixelFont);
        idTypeLabel.setForeground(c3);
        add(idTypeLabel);

        idTypeComboBox = new JComboBox<>(new String[]{"All", "Aadhar", "Passport", "Voter ID", "Driving License"});
        idTypeComboBox.setBounds(720, 100, 200, 30);
        idTypeComboBox.setBackground(c1);
        idTypeComboBox.setFont(poppins);
        idTypeComboBox.setForeground(c3);
        add(idTypeComboBox);

        // Gender Label and ComboBox
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(100, 150, 200, 30);
        genderLabel.setFont(pixelFont);
        genderLabel.setForeground(c3);
        add(genderLabel);

        genderComboBox = new JComboBox<>(new String[]{"All", "Male", "Female"});
        genderComboBox.setBounds(280, 150, 200, 30);
        genderComboBox.setBackground(c1);
        genderComboBox.setFont(poppins);
        genderComboBox.setForeground(c3);
        add(genderComboBox);

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
        scrollPane.setBounds(100, 200, 900, 380);
        add(scrollPane);

        // Buttons at the bottom
        // Search Button
        searchButton = new JButton("Search");
        searchButton.setBounds(100, 600, 180, 40);
        searchButton.setBackground(c1);
        searchButton.setFont(pixelFont);
        searchButton.setForeground(c3);
        add(searchButton);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchCustomerRecords();
            }
        });

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(400, 600, 180, 40);
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
        searchCustomerRecords();

        // Frame properties
        setUndecorated(true);
        setLayout(null);
        getContentPane().setBackground(c2);
        setLocation(350, 100);
        setSize(1090, 665);
        setVisible(true);
    }

    private void searchCustomerRecords() {
        try {
            connects c = new connects();
            String searchTerm = searchField.getText().trim();
            String idType = (String) idTypeComboBox.getSelectedItem();
            String gender = (String) genderComboBox.getSelectedItem();

            StringBuilder queryBuilder = new StringBuilder("SELECT customer_name, id_type, id_no, gender, country, allocated_room, check_in, deposite FROM customer WHERE 1=1");

            if (!searchTerm.isEmpty()) {
                queryBuilder.append(" AND (customer_name LIKE '%").append(searchTerm).append("%' OR id_no LIKE '%").append(searchTerm).append("%')");
            }
            if (!idType.equals("All")) {
                queryBuilder.append(" AND id_type = '").append(idType).append("'");
            }
            if (!gender.equals("All")) {
                queryBuilder.append(" AND gender = '").append(gender).append("'");
            }

            ResultSet resultSet = c.statement.executeQuery(queryBuilder.toString());
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error retrieving customer data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new searchCustomer();
    }
}