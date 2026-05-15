package ui;

import javax.swing.*;

public class UserLogin {public UserLogin() {JFrame frame = new JFrame("User Login");frame.setSize(300, 200);frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel panel = new JPanel();
    frame.add(panel);
    placeComponents(panel, frame);

    frame.setVisible(true);
}

private void placeComponents(JPanel panel, JFrame frame) {
    panel.setLayout(null);

    JLabel userLabel = new JLabel("Username:");
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
    loginButton.addActionListener(e -> {
        String username = userText.getText();
        String password = new String(passwordText.getPassword());

        if ("user".equals(username) && "password".equals(password)) {
            // If credentials are correct, proceed to user booking
            new UserBooking(username);
            frame.dispose(); // Close the login screen
        } else {
            // If credentials are wrong, show error message
            JOptionPane.showMessageDialog(frame, "Invalid credentials!");
        }
    });
}

}