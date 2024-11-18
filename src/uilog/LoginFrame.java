/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package uilog;

/**
 *
 * @author DMHUNG
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;

public class LoginFrame extends JFrame {
    private JTextField usernameField, passwordField;

    public LoginFrame() {
        setTitle("User Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JPanel p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
        p1.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        JLabel loginLabel = new JLabel("Login");
        loginLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        loginLabel.setFont(new Font("Arial", Font.BOLD, 24));
        p1.add(loginLabel);
        add(p1);

        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(2, 2, 10, 10));
        p2.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        usernameField = new JTextField(20);
        passwordField = new JTextField(20);
        p2.add(new JLabel("Username"));
        p2.add(usernameField);
        p2.add(new JLabel("Password"));
        p2.add(passwordField);
        add(p2);

        JPanel p3 = new JPanel();
        p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));
        p3.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(100, 30));
        loginButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        loginButton.addActionListener(new LoginButtonListener());
        p3.add(loginButton);
        add(p3);

        setVisible(true);
    }

    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();

            try {
                if (AccountUtils.checkCredentials(username, password)) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Login successful!");
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Incorrect username or password.");
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(LoginFrame.this, "Error reading account information.");
            }
        }
    }
}
