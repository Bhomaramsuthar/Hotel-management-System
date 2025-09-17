package HotelManagementSystem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class searchStaff extends JFrame {

    Color c1 = new Color(214, 143, 130);
    Color c2 = new Color(110, 76, 74);
    Color c3 = new Color(53, 28, 28);

    JTextField searchField;
    JComboBox<String> genderComboBox;
    JComboBox<String> ageComboBox;
    JButton searchButton, backButton;
    JTable table;

    searchStaff() {
        super("Search Staff Page");

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
        JLabel titleLabel = new JLabel("Search Staff");
        titleLabel.setBounds(420, 30, 450, 40);
        titleLabel.setFont(pixelFont.deriveFont(25f));
        titleLabel.setForeground(c3);
        add(titleLabel);

        // Search Label and TextField
        JLabel searchLabel = new JLabel("Search by Name:");
        searchLabel.setBounds(100, 100, 250, 30);
        searchLabel.setFont(poppins);
        searchLabel.setForeground(c3);
        add(searchLabel);

        searchField = new JTextField();
        searchField.setBounds(280, 100, 150, 30);
        searchField.setFont(poppins);
        searchField.setBackground(c1);
        searchField.setForeground(c3);
        add(searchField);

        // Gender Label and ComboBox
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(450, 100, 100, 30);
        genderLabel.setFont(poppins);
        genderLabel.setForeground(c3);
        add(genderLabel);

        genderComboBox = new JComboBox<>(new String[]{"All", "Male", "Female", "Other"});
        genderComboBox.setBounds(550, 100, 100, 30);
        genderComboBox.setBackground(c1);
        genderComboBox.setForeground(c3);
        add(genderComboBox);

        // Age Label and ComboBox
        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(680, 100, 100, 30);
        ageLabel.setFont(poppins);
        ageLabel.setForeground(c3);
        add(ageLabel);

        Vector<String> ageOptions = new Vector<>();
        ageOptions.add("All");
        ageOptions.add("18-21");
        ageOptions.add("20-30");
        ageOptions.add("30-40");
        ageOptions.add("40-50");
        ageOptions.add("60+");
        ageComboBox = new JComboBox<>(ageOptions);
        ageComboBox.setBounds(750, 100, 120, 30);
        ageComboBox.setBackground(c1);
        ageComboBox.setForeground(c3);
        add(ageComboBox);

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

        // Search Button
        searchButton = new JButton("Search");
        searchButton.setBounds(300, 600, 180, 40);
        searchButton.setBackground(c1);
        searchButton.setFont(pixelFont);
        searchButton.setForeground(c3);
        add(searchButton);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchStaffMembers();
            }
        });

        // Initial table load
        searchStaffMembers();

        // Frame properties
        setUndecorated(true);
        setLayout(null);
        getContentPane().setBackground(c2);
        setLocation(350, 100);
        setSize(1090, 665);
        setVisible(true);
    }

    private void searchStaffMembers() {
        try {
            connects c = new connects();
            String searchTerm = searchField.getText().trim();
            String gender = (String) genderComboBox.getSelectedItem();
            String ageRange = (String) ageComboBox.getSelectedItem();

            StringBuilder queryBuilder = new StringBuilder("SELECT name, age, gender, job, salary, phone, aadhar, email FROM employee WHERE 1=1");

            if (!searchTerm.isEmpty()) {
                queryBuilder.append(" AND name LIKE '%").append(searchTerm).append("%'");
            }
            if (!gender.equals("All")) {
                queryBuilder.append(" AND gender = '").append(gender).append("'");
            }
            if (!ageRange.equals("All")) {
                switch (ageRange) {
                    case "18-21":
                        queryBuilder.append(" AND CAST(age AS UNSIGNED) >= 18 AND CAST(age AS UNSIGNED) <= 21");
                        break;
                    case "20-30":
                        queryBuilder.append(" AND CAST(age AS UNSIGNED) >= 20 AND CAST(age AS UNSIGNED) <= 30");
                        break;
                    case "30-40":
                        queryBuilder.append(" AND CAST(age AS UNSIGNED) >= 30 AND CAST(age AS UNSIGNED) <= 40");
                        break;
                    case "40-50":
                        queryBuilder.append(" AND CAST(age AS UNSIGNED) >= 40 AND CAST(age AS UNSIGNED) <= 50");
                        break;
                    case "60+":
                        queryBuilder.append(" AND CAST(age AS UNSIGNED) >= 60");
                        break;
                }
            }

            // Add sorting based on search criteria
            if (!ageRange.equals("All")) {
                queryBuilder.append(" ORDER BY CAST(age AS UNSIGNED) ASC");
            } else if (!searchTerm.isEmpty()) {
                queryBuilder.append(" ORDER BY name ASC");
            }


            ResultSet resultSet = c.statement.executeQuery(queryBuilder.toString());
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error retrieving staff data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new searchStaff();
    }
}