package HotelManagementSystem;

import javax.swing.*;

public class Splash extends JFrame {
    Splash(){
        super("Splash page");
        ImageIcon imageIcon=new ImageIcon(ClassLoader.getSystemResource("icon/splash.gif"));
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0,0,900,370);
        add(label);

        setLayout(null);
        setLocation(300,200);
        setSize(900,370);
        setVisible(true);

        try{
            Thread.sleep(6000);
            new Login();
            setVisible(false);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        new Splash();
    }
}