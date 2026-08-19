package ui;

import bank.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Creating extends JFrame implements ActionListener {
    private JframeMain previousFrame;
    private JLabel heading, accHolder, pinCode;
    private JTextField textFieldHolder;
    private JPasswordField passwordFieldCode;
    private JButton create, exit;

    public Creating(JframeMain previousFrame) {
        this.previousFrame = previousFrame;
        setTitle("BANK MANAGMENT SYSTEM");
        heading = new JLabel("CREATING NEW ACCOUNT");
        heading.setFont(new Font("Osward", Font.BOLD, 25));
        heading.setBounds(120, 40, 360, 40);
        add(heading);

        accHolder = new JLabel("Name/Surname");
        accHolder.setFont(new Font("Osward", Font.BOLD, 14));
        accHolder.setBounds(50, 100, 200, 30);
        add(accHolder);

        textFieldHolder = new JTextField(40);
        textFieldHolder.setBounds(300, 100, 250, 30);
        textFieldHolder.setFont(new Font("Arial", Font.BOLD, 14));
        add(textFieldHolder);

        pinCode = new JLabel("PIN code");
        pinCode.setFont(new Font("Osward", Font.BOLD, 14));
        pinCode.setBounds(50, 150, 200, 30);
        add(pinCode);

        passwordFieldCode = new JPasswordField(40);
        passwordFieldCode.setBounds(300, 150, 250, 30);
        passwordFieldCode.setFont(new Font("Arial", Font.BOLD, 14));
        add(passwordFieldCode);

        create = new JButton("Create new account");
        create.setBackground(Color.BLACK);
        create.setForeground(Color.WHITE);
        create.setFont(new Font("Arial", Font.BOLD, 14));
        create.setBounds(150, 200, 300, 30);
        add(create);
        create.addActionListener(this);

        exit = new JButton("Back");
        exit.setBackground(Color.BLACK);
        exit.setForeground(Color.WHITE);
        exit.setFont(new Font("Arial", Font.BOLD, 14));
        exit.setBounds(150, 250, 300, 30);
        add(exit);
        exit.addActionListener(this);

        setLayout(null);

        getContentPane().setBackground(Color.WHITE);

        setSize(600,600);
        setLocation(400,100);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == create) {
            String accHolder = textFieldHolder.getText();
            String pinCode = passwordFieldCode.getText();
            if (pinCode.length() != 4) {
                JOptionPane.showMessageDialog(this, "Account is not created. PIN code should be 4 digits.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                for (char ch : pinCode.toCharArray()) {
                    if (!Character.isDigit(ch)) {
                        JOptionPane.showMessageDialog(this, "Account is not created. PIN code should contain only digits.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }
            Account account = new Account(accHolder, pinCode);
            previousFrame.getBank().addAccount(account);
            previousFrame.getDatabase().updateDatabase(previousFrame.getBank().getAccounts(), previousFrame.getBank().getNumAccounts());
            previousFrame.getDatabase().save();
            JOptionPane.showMessageDialog(this, "Account created successfully with account number: " + account.getAccNumber(), "Success", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        } else if(actionEvent.getSource() == exit){
            this.dispose();
        }
    }
}