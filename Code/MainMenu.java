package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Airline Reservation System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        JLabel label = new JLabel("Are you an Admin or User?");
        label.setBounds(120, 50, 200, 25);
        panel.add(label);

        String[] roles = {"Admin", "User"};
        JComboBox<String> comboBox = new JComboBox<>(roles);
        comboBox.setBounds(120, 80, 150, 25);
        panel.add(comboBox);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 120, 80, 25);
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String selectedRole =
                        (String) comboBox.getSelectedItem();

                if ("Admin".equals(selectedRole)) {
                    new AdminLogin();
                } else {
                    new UserLogin();
                }

                frame.dispose();
            }
        });

        frame.setVisible(true);
    }
}