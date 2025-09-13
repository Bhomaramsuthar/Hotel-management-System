package HotelManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Dashboard extends JFrame implements ActionListener {
    JButton admin, reception, logout;

    Dashboard() {
        super("Hotel Management System");

        // Load custom font
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

        // Background panel
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/Dashboard_.png"));
        Image i1 = imageIcon.getImage();
        BackgroundPanel backgroundPanel = new BackgroundPanel(i1);

        // Load button icons
        ImageIcon adminIcon = new ImageIcon(ClassLoader.getSystemResource("icon/Admin.png"));
        Image adminImg = adminIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledAdminIcon = new ImageIcon(adminImg);

        ImageIcon receptionIcon = new ImageIcon(ClassLoader.getSystemResource("icon/Reception.png"));
        Image receptionImg = receptionIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledReceptionIcon = new ImageIcon(receptionImg);

        // === Create icon+button panels ===
        JPanel adminPanel = createIconButtonPanel("Admin", scaledAdminIcon, pixelFont, "admin");
        JPanel receptionPanel = createIconButtonPanel("Reception", scaledReceptionIcon, pixelFont, "reception");

        // === Button container (centered row) ===
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 50, 20, 50);

        gbc.gridx = 0;
        buttonPanel.add(adminPanel, gbc);

        gbc.gridx = 1;
        buttonPanel.add(receptionPanel, gbc);

        // === Logout button (bottom-left) ===
        logout = new JButton("Logout");
        logout.setFont(pixelFont.deriveFont(20f));
        logout.setBackground(new Color(53, 28, 28));
        logout.setForeground(new Color(214, 143, 130));
        logout.setPreferredSize(new Dimension(200, 60));

        logout.addActionListener(e -> {
            dispose();
            new Login(); // go back to login
        });

        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        logoutPanel.setOpaque(false);
        logoutPanel.add(logout);
        logoutPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 0));

        // Add everything to background
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);
        backgroundPanel.add(logoutPanel, BorderLayout.SOUTH);

        add(backgroundPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Default size
        setSize(1000, 700);
        setLocationRelativeTo(null); // center window
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
        button.setPreferredSize(size);
        button.setMaximumSize(new Dimension(280, 60));
        button.setMinimumSize(new Dimension(200, 60));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(this);

        // Assign to class-level variables
        switch (type) {
            case "reception" -> reception = button;
            case "admin" -> admin = button;
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

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(1000, 700);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == admin) {
            new adminLogin();
            setVisible(false);
        } else if (e.getSource() == reception) {
            new Reception();
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Dashboard::new);
    }
}
