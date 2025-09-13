package HotelManagementSystem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;

public class allRooms extends JFrame implements ActionListener {

    Color c1 = new Color(214, 143, 130);
    Color c2 = new Color(110,76,74);
    Color c3 = new Color(53, 28, 28);

    JLabel l1,l2,l3,l4,l5,l6;
    JTable table;

    allRooms(){

        super("Add Employee Page");

        Font pixelFont;
        Font poppins = new Font("poppins",Font.BOLD,18);
        try {
            pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/font/PressStart2P-Regular.ttf"))
                    .deriveFont(14f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(pixelFont);
        } catch (Exception e) {
            e.printStackTrace();
            pixelFont = new Font("Serif", Font.PLAIN, 14);
        }

//label

        l1 =new JLabel("Room Details");
        l1.setBounds(300,30,650,60);
        l1.setFont(pixelFont.deriveFont(25f));
        l1.setForeground(c3);
        add(l1);

        l2 = new JLabel("Room No.");
        l2.setBounds(160,115,200,30);
        l2.setFont(poppins);
        l2.setForeground(c3);
        add(l2);

        l3 = new JLabel("Bed Type");
        l3.setBounds(320,115,200,30);
        l3.setFont(poppins);
        l3.setForeground(c3);
        add(l3);

        l4 = new JLabel("Status");
        l4.setBounds(490,115,200,30);
        l4.setFont(poppins);
        l4.setForeground(c3);
        add(l4);

        l5 = new JLabel("Availability");
        l5.setBounds(630,115,200,30);
        l5.setFont(poppins);
        l5.setForeground(c3);
        add(l5);

        l6 = new JLabel("Price");
        l6.setBounds(810,115,200,30);
        l6.setFont(poppins);
        l6.setForeground(c3);
        add(l6);

//table

        table =new JTable();
        table.setGridColor(c3);
       // table.setIntercellSpacing();
        table.setRowHeight(35);
        table.setFont(poppins);
        table.setBackground(c1);
        table.setForeground(c3);
        add(table);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);

// Put JTable inside JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(120,150,800,350);
// Instead of setBounds, it's better to use layout managers like BorderLayout

        add(scrollPane);

        try{
            connects c =new connects();
            String RoomInfo = "SELECT * FROM room";
            ResultSet resultSet = c.statement.executeQuery(RoomInfo);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        }catch (Exception e){
            e.printStackTrace();
        }

// buttons

        JButton b1 = new JButton("Back");
        b1.setBounds(100,550,180,40);
        b1.setBackground(c1);
        b1.setFont(pixelFont);
        b1.setForeground(c3);
        b1.addActionListener(this);
        add(b1);

        setUndecorated(true);
        setLayout(null);
        getContentPane().setBackground(c2);
        setLocation(350,100);
        setSize(1090,665);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

            setVisible(false);

    }

    public static void main(String[] args) {
        new allRooms();
    }
}
