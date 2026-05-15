package ui;

import javax.swing.*;import java.awt.event.ActionEvent;import java.awt.event.ActionListener;

public class AdminLogin {public AdminLogin() {JFrame frame = new JFrame("Admin Login");frame.setSize(300, 200);frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel panel = new JPanel();
    frame.add(panel);
    placeComponents(panel, frame);

    frame.setVisible(true);
}

private void placeComponents(JPanel panel, JFrame frame) {
    panel.setLayout(null);

    JLabel userLabel = new JLabel("Admin ID:");
    userLabel.setBounds(10, 20, 80, 25);
    panel.add(userLabel);

    JTextField userText = new JTextField(20);
    userText.setBounds(100, 20, 165, 25);
    panel.add(userText);

    JLabel passwordLabel = new JLabel("Password:");
    passwordLabel.setBounds(10, 50, 80, 25);
    panel.add(passwordLabel);

    JPasswordField passwordText = new JPasswordField(20);
    passwordText.setBounds(100, 50, 165, 25);
    panel.add(passwordText);

    JButton loginButton = new JButton("Login");
    loginButton.setBounds(100, 80, 80, 25);
    panel.add(loginButton);

    JButton backButton = new JButton("Back");
    backButton.setBounds(200, 80, 80, 25);
    panel.add(backButton);

    // Back Button Action
    backButton.addActionListener(e -> {
        new MainMenu();
        frame.dispose();
    });

    // Login Button Action
    loginButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String adminId = userText.getText();
            String adminPassword = new String(passwordText.getPassword());

            // Check credentials
            if ("Gokul".equals(adminId) && "Gokul@18".equals(adminPassword)) {
                JOptionPane.showMessageDialog(frame, "Login Successful!");
                new AdminDashboard();  // Navigate to Admin Dashboard
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid Admin Credentials!");
            }
        }
    });
}

}