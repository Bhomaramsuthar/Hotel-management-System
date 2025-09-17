package HotelManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Staff extends JFrame implements ActionListener{

    Color c1 = new Color(214, 143, 130);
    Color c2 = new Color(110,76,74);
    Color c3 = new Color(53, 28, 28);

    JButton department,manager,employee,back,search;

    Staff(){
        super("Staff Page");

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

        JLabel menu = new JLabel("INFO");
        menu.setBounds(30,20,260,80);
        menu.setFont(pixelFont.deriveFont(60f));
        menu.setForeground(c3);
        panel1.add(menu);

        department = new JButton("Department");
        department.setBounds(30,180,235,40);
        department.setBackground(c1);
        department.setForeground(c3);
        department.setFont(pixelFont);
        panel1.add(department);
        department.addActionListener(this);

        employee = new JButton("Employee");
        employee.setBounds(30,240,235,40);
        employee.setBackground(c1);
        employee.setForeground(c3);
        employee.setFont(pixelFont);
        panel1.add(employee);
        employee.addActionListener(this);



        manager = new JButton("Manager");
        manager.setBounds(30,300,235,40);
        manager.setBackground(c1);
        manager.setForeground(c3);
        manager.setFont(pixelFont);
        panel1.add(manager);
        manager.addActionListener(this);

        search = new JButton("Search");
        search.setBounds(30,360,235,40);
        search.setBackground(c1);
        search.setForeground(c3);
        search.setFont(pixelFont);
        panel1.add(search);
        search.addActionListener(this);

        back = new JButton("Back");
        back.setBounds(30,705,235,40);
        back.setBackground(c1);
        back.setForeground(c3);
        back.setFont(pixelFont);
        panel1.add(back);
        back.addActionListener(this);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==department){
            new Department();
        }
        else if (e.getSource()==employee){
            new allEmployee();
        }
        else if (e.getSource()==manager){
            new allManager();
        }
        else if (e.getSource()==search) {
            new searchStaff();
        }
        else {
            dispose();
            new Reception();
        }
    }

    public static void main(String[] args) {
        new Staff();
    }
}
