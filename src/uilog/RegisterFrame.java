package uilog;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;

public class RegisterFrame extends JFrame {
    private JTextField firstNameField, lastNameField, usernameField, passwordField, emailField, mobileField;

    public RegisterFrame() {
        setTitle("New User Register");
        setSize(750, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel chính chứa toàn bộ các thành phần
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        // Tiêu đề
        JLabel myLabel = new JLabel("New User Register");
        myLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        myLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(myLabel);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 15, 0));
        add(mainPanel);

        // Panel chứa các trường nhập liệu
        JPanel formPanel = new JPanel(new GridLayout(3,2, 20,20));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 20, 70, 30));
        // Tạo các trường nhập liệu và nhãn tương ứng
        firstNameField = new JTextField(15);
        lastNameField = new JTextField(15);
        usernameField = new JTextField(15);
        passwordField = new JTextField(15);
        emailField = new JTextField(15);
        mobileField = new JTextField(15);

        // Thêm các nhãn và trường nhập liệu vào formPanel
        formPanel.add(new JLabel("First name", JLabel.RIGHT));
        formPanel.add(firstNameField);
        formPanel.add(new JLabel("Username", JLabel.RIGHT));
        formPanel.add(usernameField);

        formPanel.add(new JLabel("Last name", JLabel.RIGHT));
        formPanel.add(lastNameField);
        formPanel.add(new JLabel("Password", JLabel.RIGHT));
        formPanel.add(passwordField);

        formPanel.add(new JLabel("Email address", JLabel.RIGHT));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Mobile number", JLabel.RIGHT));
        formPanel.add(mobileField);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Panel chứa các nút Register và Login
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new RegisterButtonListener());

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            dispose();
            new LoginFrame();
        });

        buttonPanel.add(registerButton);
        buttonPanel.add(Box.createHorizontalStrut(500)); 
        buttonPanel.add(loginButton);

        mainPanel.add(buttonPanel);

        // Thêm mainPanel vào JFrame
        add(mainPanel);
        setVisible(true);
        }

        private class RegisterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();
            String email = emailField.getText().trim();
            String mobile = mobileField.getText().trim();

            try {
                if (AccountUtils.isUsernameExists(username)) {
                    JOptionPane.showMessageDialog(RegisterFrame.this, "Username is already taken");
                } else {
                    AccountUtils.saveAccount(firstName, lastName, username, password, email, mobile);
                    JOptionPane.showMessageDialog(RegisterFrame.this, "Registration Successful!");
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(RegisterFrame.this, "Error saving account information.");
            }
        }
    }
}
