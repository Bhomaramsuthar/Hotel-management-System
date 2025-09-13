package HotelManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Room extends JFrame {

    Color c1 = new Color(214, 143, 130);
    Color c2 = new Color(110,76,74);
    Color c3 = new Color(53, 28, 28);

    JButton btnSR,btnAR,btnUR,back;

    Room(){
        super("Room Page");

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

        btnAR = new JButton("All Rooms");
        btnAR.setBounds(30,180,235,40);
        btnAR.setBackground(c1);
        btnAR.setForeground(c3);
        btnAR.setFont(pixelFont);
        panel1.add(btnAR);
        btnAR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new allRooms();
            }
        });

        btnSR = new JButton("Search Room");
        btnSR.setBounds(30,240,235,40);
        btnSR.setBackground(c1);
        btnSR.setForeground(c3);
        btnSR.setFont(pixelFont);
        panel1.add(btnSR);

        btnUR = new JButton("Update Room");
        btnUR.setBounds(30,300,235,40);
        btnUR.setBackground(c1);
        btnUR.setForeground(c3);
        btnUR.setFont(pixelFont);
        panel1.add(btnUR);

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
        new Room();
    }
}
