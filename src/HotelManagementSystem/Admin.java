package HotelManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Admin extends JFrame implements ActionListener {
    JButton addEmployee, back;

    Admin() {
        super("Admin Page");

        // Load custom font
        Font pixelFont;
        try {
            pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/font/PressStart2P-Regular.ttf"))
                    .deriveFont(16f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(pixelFont);
        } catch (Exception e) {
            e.printStackTrace();
            pixelFont = new Font("Serif", Font.PLAIN, 18);
        }

        // Background panel
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/Dashboard_.png"));
        Image backgroundImg = imageIcon.getImage();
        BackgroundPanel backgroundPanel = new BackgroundPanel(backgroundImg);

        // === Load and scale icons ===

        ImageIcon addIcon = new ImageIcon(ClassLoader.getSystemResource("icon/new.png"));
        Image addImg = addIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);

        // === Create Panels (icon + button) ===
       JPanel addPanel = createIconButtonPanel("Add Employee", new ImageIcon(addImg), pixelFont, "add");

        // === Main button container (row of 4, centered) ===
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 50, 20, 50);

        gbc.gridx = 0;
        buttonPanel.add(addPanel, gbc);

        // === Logout button ===
        back = new JButton("Back");
        back.setFont(pixelFont.deriveFont(20f));
        back.setBackground(new Color(53, 28, 28));
        back.setForeground(new Color(214, 143, 130));


        Dimension logoutSize = new Dimension(200, 80);
        back.setPreferredSize(logoutSize);

        back.addActionListener(e -> {
            dispose();
            new Dashboard(); // back to login
        });

        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        logoutPanel.setOpaque(false);
        logoutPanel.add(back);
        logoutPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 0));

        // Add everything to background
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);
        backgroundPanel.add(logoutPanel, BorderLayout.SOUTH);

        add(backgroundPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // fullscreen
        setVisible(true);
    }

    // === Reusable method to build icon+button panels ===
    private JPanel createIconButtonPanel(String text, ImageIcon icon, Font font, String type) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        JLabel label = new JLabel(icon);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton button = new JButton(text);
        button.setFont(font.deriveFont(16f));
        button.setBackground(new Color(53, 28, 28));
        button.setForeground(new Color(214, 143, 130));

        Dimension size = new Dimension(250, 60);
        Dimension sizem = new Dimension(200, 60);
        Dimension sizeM = new Dimension(280, 60);

        button.setPreferredSize(size);
        button.setMaximumSize(sizeM);
        button.setMinimumSize(sizem);

        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(this);

        // Assign to class-level variables
        switch (type) {
            case "add" -> addEmployee = button;
        }

        panel.add(label);
        panel.add(Box.createVerticalStrut(10));
        panel.add(button);

        return panel;
    }

    // === Background panel with scaling image ===
    class BackgroundPanel extends JPanel {
        private final Image backgroundImage;

        public BackgroundPanel(Image image) {
            this.backgroundImage = image;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
         if (src == addEmployee) {
            new AddEmployeeOrRoom();
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Admin::new);
    }
}
