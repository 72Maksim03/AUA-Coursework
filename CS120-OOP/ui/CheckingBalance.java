package ui;

import bank.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckingBalance extends JFrame implements ActionListener {
    private JframeMain previousFrame;
    private JLabel heading, accHolder, pinCode;
    private JTextField textFieldHolder;
    private JPasswordField passwordFieldCode;
    private JButton enter, exit;

    public CheckingBalance(JframeMain previousFrame) {
        this.previousFrame = previousFrame;
        setTitle("BANK MANAGMENT SYSTEM");
        heading = new JLabel("CHECKING THE BALANCE");
        heading.setFont(new Font("Osward", Font.BOLD, 25));
        heading.setBounds(120, 40, 360, 40);
        add(heading);

        accHolder = new JLabel("Account Number");
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

        enter = new JButton("Check");
        enter.setBackground(Color.BLACK);
        enter.setForeground(Color.WHITE);
        enter.setFont(new Font("Arial", Font.BOLD, 14));
        enter.setBounds(150, 200, 300, 30);
        add(enter);
        enter.addActionListener(this);

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
        if (actionEvent.getSource() == enter) {
            String accNumber = textFieldHolder.getText();
            String pin = new String(passwordFieldCode.getPassword());
            Account account = previousFrame.getBank().findAccountByAccNumber(accNumber);
            if (account != null) {
                if (account.validatePin(pin)) {
                    JOptionPane.showMessageDialog(this, "Balance: " + account.getBalance(), "Balance", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid PIN code", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No such account found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if(actionEvent.getSource() == exit){
            this.dispose();
        }
    }
}