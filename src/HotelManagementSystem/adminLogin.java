package HotelManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class adminLogin extends JFrame implements ActionListener {
    public
    JLabel label1,label2;
    JTextField t1;
    JPasswordField passwordField;
    JButton b1,b2;

    adminLogin() {
        super("Admin Login");

        Font pixelFont = null;

        try {
            pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/font/PressStart2P-Regular.ttf"))
                    .deriveFont(14f); // adjust size as needed

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(pixelFont);
        } catch (Exception e) {
            e.printStackTrace();

            pixelFont = new Font("Serif", Font.PLAIN, 14);
        }

        label1 = new JLabel("Username");
        label1.setBounds(40, 40, 200, 30);
        label1.setFont(pixelFont);
        label1.setForeground(new Color(214, 143, 130));
        add(label1);

        t1 = new JTextField();
        t1.setBounds(180, 40, 150, 30);
        t1.setFont(new Font("poppins",Font.BOLD,16));
        t1.setForeground(new Color(53, 28, 28));
        t1.setBackground(new Color(214, 143, 130));
        add(t1);

        label2 = new JLabel("Password");
        label2.setBounds(40, 90, 200, 30);
        label2.setFont(pixelFont);
        label2.setForeground(new Color(214, 143, 130));
        add(label2);

        passwordField = new JPasswordField();
        passwordField.setBounds(180, 90, 150, 30);
        passwordField.setFont(new Font("poppins",Font.BOLD,16));
        passwordField.setForeground(new Color(53, 28, 28));
        passwordField.setBackground(new Color(214, 143, 130));
        add(passwordField);

        b1 = new JButton("Login");
        b1.setBounds(40, 160, 120, 30);
        b1.setFont(pixelFont);
        b1.setBackground(new Color(214, 143, 130));
        b1.setForeground(new Color(53, 28, 28));
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Cancel");
        b2.setBounds(200, 160, 130, 30);
        b2.setFont(pixelFont);
        b2.setBackground(new Color(214, 143, 130));
        b2.setForeground(new Color(53, 28, 28));
        b2.addActionListener(this);
        add(b2);

        ImageIcon lift =new ImageIcon(ClassLoader.getSystemResource("icon/lift.gif"));
        Image i1 = lift.getImage().getScaledInstance(151,151,Image.SCALE_DEFAULT);
        JLabel label = new JLabel(lift);
        label.setBounds(380,40,151,151);

        add(label);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(110,76,74));
        setLayout(null);
        setLocation(400, 270);
        setSize(600, 300);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == b1){
            String user = t1.getText();
            String pass = new String(passwordField.getPassword());

            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter both Username & Password");
                return;
            }

            try {
                connects c = new connects();
                // Use a PreparedStatement to prevent SQL Injection
                String query = "SELECT roles FROM login WHERE username = ? AND password = ?";
                PreparedStatement pstmt = c.connection.prepareStatement(query);
                pstmt.setString(1, user);
                pstmt.setString(2, pass);

                ResultSet resultSet = pstmt.executeQuery();

                if (resultSet.next()){
                    String userRole = resultSet.getString("roles");
                    if ("Admin".equalsIgnoreCase(userRole)){
                        setVisible(false);
                        new Admin();
                    } else {
                        JOptionPane.showMessageDialog(null, "Access Denied: You do not have Admin privileges.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password.");
                }

                // Close resources to prevent memory leaks
                resultSet.close();
                pstmt.close();
                c.connection.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database Error: " + ex.getMessage());
            }
        }
        else{
            dispose();
            new Dashboard(); // Assuming this is the class for the main dashboard
        }
    }

    public static void main(String[] args) {
        new adminLogin();
    }
}