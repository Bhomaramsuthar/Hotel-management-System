package HotelManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AddEmployeeOrRoom extends JFrame implements ActionListener{

    Color c1 = new Color(214, 143, 130);
    Color c2 = new Color(110,76,74);
    Color c3 = new Color(53, 28, 28);

    JButton addDr,addRo,addEmp,back;

    AddEmployeeOrRoom(){
        super("Add Employee or Room Page");

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

        addEmp = new JButton("Add Employee");
        addEmp.setBounds(30,180,235,40);
        addEmp.setBackground(c1);
        addEmp.setForeground(c3);
        addEmp.setFont(pixelFont);
        panel1.add(addEmp);
        addEmp.addActionListener(this);

        addRo = new JButton("Add Room");
        addRo.setBounds(30,240,235,40);
        addRo.setBackground(c1);
        addRo.setForeground(c3);
        addRo.setFont(pixelFont);
        panel1.add(addRo);
        addRo.addActionListener(this);

        addDr = new JButton("Add Driver");
        addDr.setBounds(30,300,235,40);
        addDr.setBackground(c1);
        addDr.setForeground(c3);
        addDr.setFont(pixelFont);
        panel1.add(addDr);
        addDr.addActionListener(this);

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

        getContentPane().setBackground(c3);
        setLayout(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addRo){
            try {
                new addRoom();

            }catch (Exception E){
                E.printStackTrace();
            }
        } else if (e.getSource()==addEmp) {
            try {
                new addEmployee();

            }catch (Exception E){
                E.printStackTrace();
            }
        }else if (e.getSource()==addDr){
            try {
                new addDriver();

            }catch (Exception E){
                E.printStackTrace();
            }

        }else {
            new Admin();
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new AddEmployeeOrRoom();
    }
}
