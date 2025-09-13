package HotelManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class addDriver extends JFrame implements ActionListener {

    Color c1 = new Color(214, 143, 130);
    Color c2 = new Color(110,76,74);
    Color c3 = new Color(53, 28, 28);


    JTextField jt1,jt2,jt3,jt4,jt5;
    JComboBox jc1,jc2;
    JButton b1,b2;
    JLabel l1,l2,l3,l4,l5,l6,l7,l8;


    addDriver(){

        super("Add Driver Page");

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

        l1 = new JLabel("Add Driver Details");
        l1.setBounds(300,30,650,60);
        l1.setFont(pixelFont.deriveFont(25f));
        l1.setForeground(c3);
        add(l1);

        l2 = new JLabel("Name");
        l2.setBounds(120,100,200,30);
        l2.setFont(pixelFont);
        l2.setForeground(c3);
        add(l2);

        l3 = new JLabel("Age");
        l3.setBounds(120,150,200,30);
        l3.setFont(pixelFont);
        l3.setForeground(c3);
        add(l3);

        l4 = new JLabel("Gender");
        l4.setBounds(120,200,200,30);
        l4.setFont(pixelFont);
        l4.setForeground(c3);
        add(l4);

        l5 = new JLabel("Car Company");
        l5.setBounds(120,250,200,30);
        l5.setFont(pixelFont);
        l5.setForeground(c3);
        add(l5);

        l6 = new JLabel("Car Name");
        l6.setBounds(120,300,200,30);
        l6.setFont(pixelFont);
        l6.setForeground(c3);
        add(l6);

        l7 = new JLabel("Availability");
        l7.setBounds(120,350,200,30);
        l7.setFont(pixelFont);
        l7.setForeground(c3);
        add(l7);

        l8 = new JLabel("Location");
        l8.setBounds(120,400,200,30);
        l8.setFont(pixelFont);
        l8.setForeground(c3);
        add(l8);

//textfields

        jt1 = new JTextField();
        jt1.setBounds(340,100,200,30);
        jt1.setForeground(c3);
        jt1.setBackground(c1);
        jt1.setFont(new Font("poppins",Font.BOLD,18));
        add(jt1);

        jt2 = new JTextField();
        jt2.setBounds(340,150,200,30);
        jt2.setForeground(c3);
        jt2.setBackground(c1);
        jt2.setFont(new Font("poppins",Font.BOLD,18));
        add(jt2);

        jt3 = new JTextField();
        jt3.setBounds(340,250,200,30);
        jt3.setForeground(c3);
        jt3.setBackground(c1);
        jt3.setFont(new Font("poppins",Font.BOLD,18));
        add(jt3);

        jt4 = new JTextField();
        jt4.setBounds(340,300,200,30);
        jt4.setForeground(c3);
        jt4.setBackground(c1);
        jt4.setFont(new Font("poppins",Font.BOLD,18));
        add(jt4);

        jt5 = new JTextField();
        jt5.setBounds(340,400,200,30);
        jt5.setForeground(c3);
        jt5.setBackground(c1);
        jt5.setFont(new Font("poppins",Font.BOLD,18));
        add(jt5);

//combobox

        jc1 = new JComboBox(new String[] {"Select","Male","Female"});
        jc1.setBounds(340,200,200,30);
        jc1.setForeground(c3);
        jc1.setBackground(c1);
        jc1.setFont(new Font("poppins",Font.BOLD,18));
        add(jc1);

        jc2 = new JComboBox(new String[] {"Select","Yes","No"});
        jc2.setBounds(340,350,200,30);
        jc2.setForeground(c3);
        jc2.setBackground(c1);
        jc2.setFont(new Font("poppins",Font.BOLD,18));
        add(jc2);

//buttons

        b1 = new JButton("Add");
        b1.setBounds(100,530,180,40);
        b1.setBackground(c1);
        b1.setFont(pixelFont);
        b1.setForeground(c3);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Back");
        b2.setBounds(400,530,180,40);
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
            try{
                String name = jt1.getText();
                String age = jt2.getText();
                String gender = (String) jc1.getSelectedItem();
                String car_company = jt3.getText();
                String car_name =jt4.getText();
                String available = (String) jc2.getSelectedItem();
                String location =jt5.getText();

                if (name.isEmpty() || age.isEmpty() ||
                         "Select".equals(gender) ||
                        car_company.isEmpty() || car_name.isEmpty() ||
                        "Select".equals(available)|| location.isEmpty() ){

                    JOptionPane.showMessageDialog(this,
                            "Please fill all fields before saving!",
                            "Missing Information",JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                //all input are completed

                connects c =new connects();
                String q = "INSERT INTO driver VALUES ('"+name+"','"+age+"','"+gender+"','"+car_company+"','"+car_name+"','"+available+"','"+location+"')";
                c.statement.executeUpdate(q);

                JOptionPane.showMessageDialog(this,
                        "Driver Successfully Added");
                setVisible(false);


            }catch (Exception E){
                E.printStackTrace();
            }

        }else{
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new addDriver();
    }
}
