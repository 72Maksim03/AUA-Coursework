package ui;

import bank.Account;
import bank.Transactions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Remove extends JFrame implements ActionListener {
    private JframeMain previousFrame;
    private JLabel heading, accHolder, pinCode;
    private JTextField textFieldHolder;
    private JPasswordField passwordFieldCode;
    private JButton Delete, exit;

    public Remove(JframeMain previousFrame) {
        this.previousFrame = previousFrame;
        setTitle("BANK MANAGMENT SYSTEM");
        heading = new JLabel("Delete account");
        heading.setFont(new Font("Osward", Font.BOLD, 38));
        heading.setBounds(200,40,300,40);
        add(heading);

        accHolder = new JLabel("Account number");
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

        Delete = new JButton("Delete");
        Delete.setBackground(Color.BLACK);
        Delete.setForeground(Color.WHITE);
        Delete.setFont(new Font("Arial", Font.BOLD, 14));
        Delete.setBounds(150, 200, 300, 30);
        add(Delete);
        Delete.addActionListener(this);

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
        if (actionEvent.getSource() == Delete) {
            String accNumber = textFieldHolder.getText();
            String pin = new String(passwordFieldCode.getPassword());
            Account account = previousFrame.getBank().findAccountByAccNumber(accNumber);
            if (account != null) {
                previousFrame.getBank().removeAccount(account, pin);
                previousFrame.getDatabase().updateDatabase(previousFrame.getBank().getAccounts(), previousFrame.getBank().getNumAccounts());
                previousFrame.getDatabase().save();
                JOptionPane.showMessageDialog(this, "Account deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No such account found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if(actionEvent.getSource() == exit){
            this.dispose();
        }
    }
}