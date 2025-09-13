package HotelManagementSystem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;

public class allManager extends JFrame {


    Color c1 = new Color(214, 143, 130);
    Color c2 = new Color(110,76,74);
    Color c3 = new Color(53, 28, 28);

    JLabel l1;
    JTable table;

    allManager(){

        super("All employee Page");

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


//labels

        l1 =new JLabel("Manager Details");
        l1.setBounds(300,30,750,60);
        l1.setFont(pixelFont.deriveFont(25f));
        l1.setForeground(c3);
        add(l1);



//table

// Create JTable with no data initially
        JTable table = new JTable();
        table.setFont(poppins.deriveFont(14f));
        table.setForeground(c3);
        table.setBackground(c1);
        table.setRowHeight(35);
        table.setGridColor(c3);

// Make table responsive to content
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);

// Put JTable inside JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(120, 150, 900, 175); // optional if you're still using null layout
// Instead of setBounds, it's better to use layout managers like BorderLayout

        add(scrollPane);  // add the scrollPane, not the table directly


        try{

            connects c = new connects();
            String EmpInfo = "SELECT * FROM employee WHERE job = 'Manager' ";
            ResultSet resultSet = c.statement.executeQuery(EmpInfo);
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
        add(b1);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

// background

        setUndecorated(true);
        setLayout(null);
        getContentPane().setBackground(c2);
        setLocation(350,100);
        setSize(1090,665);
        setVisible(true);


    }



    public static void main(String[] args) {
        new allManager();
    }
}
