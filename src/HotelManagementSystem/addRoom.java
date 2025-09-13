package HotelManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class addRoom extends JFrame implements ActionListener {

    Color c1 = new Color(214, 143, 130);
    Color c2 = new Color(110,76,74);
    Color c3 = new Color(53, 28, 28);

    JTextField jt1,jt2;
    JComboBox jc1,jc2,jc3;
    JButton b1,b2;
    JLabel l1,l2,l3,l4,l5,l6;

    addRoom(){

        super("Add Room Page");

        Font pixelFont;
        try {
            pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/font/PressStart2P-Regular.ttf"))
                    .deriveFont(16f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(pixelFont);
        } catch (Exception e) {
            e.printStackTrace();
            pixelFont = new Font("Serif", Font.PLAIN, 14);
        }

//labels

        l1 = new JLabel("Add Rooms");
        l1.setBounds(400,40,350,60);
        l1.setFont(pixelFont.deriveFont(30f));
        l1.setForeground(c3);
        add(l1);

        l2 = new JLabel("Room Number");
        l2.setBounds(64,130,200,30);
        l2.setFont(pixelFont);
        l2.setForeground(c3);
        add(l2);

        l3 = new JLabel("Availability");
        l3.setBounds(64,200,200,30);
        l3.setFont(pixelFont);
        l3.setForeground(c3);
        add(l3);

        l4 = new JLabel("Room Price");
        l4.setBounds(64,270,200,30);
        l4.setFont(pixelFont);
        l4.setForeground(c3);
        add(l4);

        l5 = new JLabel("Cleaning Status");
        l5.setBounds(64,340,250,30);
        l5.setFont(pixelFont);
        l5.setForeground(c3);
        add(l5);

        l6 = new JLabel("Bed Type");
        l6.setBounds(64,410,200,30);
        l6.setFont(pixelFont);
        l6.setForeground(c3);
        add(l6);

//textfield here

        jt1 = new JTextField();
        jt1.setBounds(390,130,200,30);
        jt1.setForeground(c3);
        jt1.setBackground(c1);
        jt1.setFont(new Font("poppins",Font.BOLD,18));
        add(jt1);

        jt2 = new JTextField();
        jt2.setBounds(390,270,200,30);
        jt2.setForeground(c3);
        jt2.setBackground(c1);
        jt2.setFont(new Font("poppins",Font.BOLD,18));
        add(jt2);



//comboBox

        jc1 = new JComboBox(new String[]{"Select","Available", "Occupied"});
        jc1.setFont(new Font("poppins",Font.BOLD,18));
        jc1.setBounds(390,200,200,30);
        jc1.setBackground(c1);
        jc1.setForeground(c3);
        add(jc1);

        jc2 = new JComboBox(new String[]{"Select","Cleaned", "Dirty"});
        jc2.setFont(new Font("poppins",Font.BOLD,18));
        jc2.setBounds(390,340,200,30);
        jc2.setBackground(c1);
        jc2.setForeground(c3);
        add(jc2);

        jc3 = new JComboBox(new String[]{"Select","Single Bed", "Double Bed"});
        jc3.setFont(new Font("poppins",Font.BOLD,18));
        jc3.setBounds(390,410,200,30);
        jc3.setBackground(c1);
        jc3.setForeground(c3);
        add(jc3);

//buttons

        b1 = new JButton("Add");
        b1.setBounds(100,480,180,40);
        b1.setBackground(c1);
        b1.setFont(pixelFont);
        b1.setForeground(c3);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Back");
        b2.setBounds(400,480,180,40);
        b2.setBackground(c1);
        b2.setFont(pixelFont);
        b2.setForeground(c3);
        b2.addActionListener(this);
        add(b2);

//background

        setUndecorated(true);
        setLayout(null);
        getContentPane().setBackground(c2);
        setLocation(350,100);
        setSize(1090,665);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            try {
                String room = jt1.getText().trim();
                String availability = (String) jc1.getSelectedItem();
                String status = (String) jc2.getSelectedItem();
                String price = jt2.getText().trim();
                String type = (String) jc3.getSelectedItem();

                // Validation: check if fields are empty or default
                if (room.isEmpty() || price.isEmpty() ||
                        "Select".equals(availability) ||
                        "Select".equals(status) ||
                        "Select".equals(type)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please fill all fields before saving!",
                            "Missing Information",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return; // stop execution if validation fails
                }

                // If all fields are valid -> insert into DB
                connects c = new connects();
                String q = "INSERT INTO room VALUES ('" + room + "','" + type + "','" + status + "','" + availability + "','" + price + "')";
                c.statement.executeUpdate(q);

                JOptionPane.showMessageDialog(this, "Room Successfully Added");
                setVisible(false);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }


    public static void main(String[] args) {
        new addRoom();
    }
}
