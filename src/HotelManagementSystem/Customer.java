package HotelManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Customer extends JFrame {

    Color c1 = new Color(214, 143, 130);
    Color c2 = new Color(110,76,74);
    Color c3 = new Color(53, 28, 28);

    JButton newCustomer,updateCustomer,checkOut,extraFees,customerInfo,pickUp,back;

    Customer(){
        super("Customer Page");

        Font pixelFont;
        try {
            pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/font/PressStart2P-Regular.ttf"))
                    .deriveFont(14f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(pixelFont);
        } catch (Exception e) {
            e.printStackTrace();
            pixelFont = new Font("Serif", Font.PLAIN, 14);
        }

//left panel

        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBounds(10,10,300,765);
        panel1.setBackground(c2);
        add(panel1);

        JLabel menu = new JLabel("MENU");
        menu.setBounds(30,20,260,80);
        menu.setFont(pixelFont.deriveFont(60f));
        menu.setForeground(c3);
        panel1.add(menu);

        newCustomer = new JButton("New Customer");
        newCustomer.setBounds(20,180,255,40);
        newCustomer.setBackground(c1);
        newCustomer.setForeground(c3);
        newCustomer.setFont(pixelFont);
        panel1.add(newCustomer);

        updateCustomer = new JButton("Update Customer");
        updateCustomer.setBounds(20,240,255,40);
        updateCustomer.setBackground(c1);
        updateCustomer.setForeground(c3);
        updateCustomer.setFont(pixelFont);
        panel1.add(updateCustomer);

        extraFees = new JButton("Extra Fees");
        extraFees.setBounds(20,300,255,40);
        extraFees.setBackground(c1);
        extraFees.setForeground(c3);
        extraFees.setFont(pixelFont);
        panel1.add(extraFees);

        customerInfo = new JButton("Customer Info");
        customerInfo.setBounds(20,360,255,40);
        customerInfo.setBackground(c1);
        customerInfo.setForeground(c3);
        customerInfo.setFont(pixelFont);
        panel1.add(customerInfo);

        pickUp = new JButton("Pick-up");
        pickUp.setBounds(20,420,255,40);
        pickUp.setBackground(c1);
        pickUp.setForeground(c3);
        pickUp.setFont(pixelFont);
        panel1.add(pickUp);

        checkOut = new JButton("Check Out");
        checkOut.setBounds(20,480,255,40);
        checkOut.setBackground(c1);
        checkOut.setForeground(c3);
        checkOut.setFont(pixelFont);
        panel1.add(checkOut);

        back = new JButton("Back");
        back.setBounds(30,705,235,40);
        back.setBackground(c1);
        back.setForeground(c3);
        back.setFont(pixelFont);
        panel1.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Reception();
            }
        });

//right panel

        JPanel panel2 = new JPanel();
        panel2.setLayout(null);
        panel2.setBounds(320,10,1200,765);
        panel2.setBackground(c2);
        add(panel2);

        ImageIcon i1 =new ImageIcon(ClassLoader.getSystemResource("icon/dummy.gif"));
        Image i2 = i1.getImage().getScaledInstance(400,400,Image.SCALE_DEFAULT);
        ImageIcon imageIcon = new ImageIcon(i2);
        JLabel dummy =new JLabel(imageIcon);
        dummy.setBounds(200,20,800,800);
        panel2.add(dummy);

//background


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // fullscreen
        getContentPane().setBackground(c3);
        setLayout(null);
        setSize(1920,1080);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Customer();
    }
}
