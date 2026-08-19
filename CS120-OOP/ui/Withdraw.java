package ui;

import bank.Account;
import bank.Transactions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Withdraw extends JFrame implements ActionListener {
    private JframeMain previousFrame;
    private JLabel heading, accHolder, amount, pinCode;
    private JTextField textFieldHolder, wdAmount;
    private JPasswordField passwordFieldCode;
    private JButton Withdraw, exit;

    public Withdraw(JframeMain previousFrame) {
        this.previousFrame = previousFrame;
        setTitle("BANK MANAGMENT SYSTEM");
        heading = new JLabel("Withdraw");
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

        amount = new JLabel("Amount to withdraw");
        amount.setFont(new Font("Osward", Font.BOLD, 14));
        amount.setBounds(50, 200, 200, 30);
        add(amount);

        wdAmount = new JTextField(40);
        wdAmount.setBounds(300, 200, 250, 30);
        wdAmount.setFont(new Font("Arial", Font.BOLD, 14));
        add(wdAmount);

        Withdraw = new JButton("Withdraw");
        Withdraw.setBackground(Color.BLACK);
        Withdraw.setForeground(Color.WHITE);
        Withdraw.setFont(new Font("Arial", Font.BOLD, 14));
        Withdraw.setBounds(150, 250, 300, 30);
        add(Withdraw);
        Withdraw.addActionListener(this);

        exit = new JButton("Back");
        exit.setBackground(Color.BLACK);
        exit.setForeground(Color.WHITE);
        exit.setFont(new Font("Arial", Font.BOLD, 14));
        exit.setBounds(150, 300, 300, 30);
        add(exit);
        exit.addActionListener(this);

        setLayout(null);

        getContentPane().setBackground(Color.WHITE);

        setSize(600,600);
        setLocation(400,100);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == Withdraw) {
            String accHolder = textFieldHolder.getText();
            double amoutToWd;
            String pinCode = new String(passwordFieldCode.getPassword());
            try {
                amoutToWd = Double.parseDouble(wdAmount.getText());
                Account account = previousFrame.getBank().findAccountByAccNumber(accHolder);
                if (account != null && account.validatePin(pinCode)) {
                    if (account.getBalance() >= amoutToWd) {
                        new Transactions().withdraw(account, amoutToWd);
                        previousFrame.getDatabase().updateDatabase(previousFrame.getBank().getAccounts(), previousFrame.getBank().getNumAccounts());
                        previousFrame.getDatabase().save();
                        JOptionPane.showMessageDialog(this, "Withdrawal successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Insufficient balance.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid account number or PIN.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid amount format.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if(actionEvent.getSource() == exit){
            this.dispose();
        }
    }
}