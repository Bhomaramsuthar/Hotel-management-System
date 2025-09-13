package HotelManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;

public class newCustomer extends JFrame implements ActionListener {


    Color c1 = new Color(214, 143, 130);
    Color c2 = new Color(110,76,74);
    Color c3 = new Color(53, 28, 28);

    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9;
    JButton b1,b2;
    JTextField jtName,jtIdNo,jtCountry,jtDepo;
    JComboBox jc1;
    JRadioButton male,female;

    newCustomer(){
        super("Add Employee Page");

        Font pixelFont;
        Font poppins = new Font("poppins",Font.BOLD,18);
        try {
            pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/font/PressStart2P-Regular.ttf"))
                    .deriveFont(16f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(pixelFont);
        } catch (Exception e) {
            e.printStackTrace();
            pixelFont = new Font("Serif", Font.PLAIN, 14);
        }

//label

        l1 = new JLabel("Add Customer Details");
        l1.setBounds(300,30,650,60);
        l1.setFont(pixelFont.deriveFont(25f));
        l1.setForeground(c3);
        add(l1);

        l2 = new JLabel("Name");
        l2.setBounds(120,100,200,30);
        l2.setFont(pixelFont);
        l2.setForeground(c3);
        add(l2);

        l3 = new JLabel("ID");
        l3.setBounds(120,150,200,30);
        l3.setFont(pixelFont);
        l3.setForeground(c3);
        add(l3);

        l4 = new JLabel("ID number");
        l4.setBounds(120,200,200,30);
        l4.setFont(pixelFont);
        l4.setForeground(c3);
        add(l4);

        l5 = new JLabel("Gender");
        l5.setBounds(120,250,200,30);
        l5.setFont(pixelFont);
        l5.setForeground(c3);
        add(l5);

        l6 = new JLabel("Country");
        l6.setBounds(120,300,200,30);
        l6.setFont(pixelFont);
        l6.setForeground(c3);
        add(l6);

        l7 = new JLabel("Allocated Room");
        l7.setBounds(120,350,200,30);
        l7.setFont(pixelFont);
        l7.setForeground(c3);
        add(l7);

        l8 = new JLabel("Check-IN");
        l8.setBounds(120,400,200,30);
        l8.setFont(pixelFont);
        l8.setForeground(c3);
        add(l8);

        l9 = new JLabel("Deposite");
        l9.setBounds(120,450,200,30);
        l9.setFont(pixelFont);
        l9.setForeground(c3);
        add(l9);

//textfield

        jtName = new JTextField();
        jtName.setBounds(340,100,200,30);
        jtName.setForeground(c3);
        jtName.setBackground(c1);
        jtName.setFont(poppins);
        add(jtName);

        jtIdNo = new JTextField();
        jtIdNo.setBounds(340,200,200,30);
        jtIdNo.setForeground(c3);
        jtIdNo.setBackground(c1);
        jtIdNo.setFont(poppins);
        add(jtIdNo);

        jtCountry = new JTextField();
        jtCountry.setBounds(340,300,200,30);
        jtCountry.setForeground(c3);
        jtCountry.setBackground(c1);
        jtCountry.setFont(poppins);
        add(jtCountry);

        jtDepo = new JTextField();
        jtDepo.setBounds(340,100,450,30);
        jtDepo.setForeground(c3);
        jtDepo.setBackground(c1);
        jtDepo.setFont(poppins);
        add(jtDepo);

//combobox

        jc1 = new JComboBox(new String[] {"Select","Aadhar","Passport","Voter ID","Driving License"});
        jc1.setBounds(340,150,200,30);
        jc1.setForeground(c3);
        jc1.setBackground(c1);
        jc1.setFont(poppins);
        add(jc1);

//custom choice from the database

        Choice ch1=new Choice();
        try {

            connects c =new connects();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM room WHERE availability = 'Available' ");

        }catch (Exception e){
            e.printStackTrace();
        }


//butons

        b1 = new JButton("Save");
        b1.setBounds(100,550,180,40);
        b1.setBackground(c1);
        b1.setFont(pixelFont);
        b1.setForeground(c3);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Back");
        b2.setBounds(400,550,180,40);
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
        if (e.getSource()==b1){
            ;
        }else {
            dispose();
        }
    }

    public static void main(String[] args) {
        new newCustomer();
    }
}
