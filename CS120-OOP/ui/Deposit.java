package ui;

import bank.Account;
import bank.Transactions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Deposit extends JFrame implements ActionListener {
    private JframeMain previousFrame;
    private JLabel heading, accHolder, amount;
    private JTextField textFieldHolder, depAmount;
    private JButton Deposit, exit;

    public Deposit(JframeMain previousFrame) {
        this.previousFrame = previousFrame;
        setTitle("BANK MANAGMENT SYSTEM");
        heading = new JLabel("Deposit");
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

        amount = new JLabel("Amount to deposit");
        amount.setFont(new Font("Osward", Font.BOLD, 14));
        amount.setBounds(50, 150, 200, 30);
        add(amount);

        depAmount = new JTextField(40);
        depAmount.setBounds(300, 150, 250, 30);
        depAmount.setFont(new Font("Arial", Font.BOLD, 14));
        add(depAmount);

        Deposit = new JButton("Deposit");
        Deposit.setBackground(Color.BLACK);
        Deposit.setForeground(Color.WHITE);
        Deposit.setFont(new Font("Arial", Font.BOLD, 14));
        Deposit.setBounds(150, 200, 300, 30);
        add(Deposit);
        Deposit.addActionListener(this);

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
        if (actionEvent.getSource() == Deposit) {
            String accHolder = textFieldHolder.getText();
            double amoutForDep;
            try {
                amoutForDep = Double.parseDouble(depAmount.getText());
                Account account = previousFrame.getBank().findAccountByAccNumber(accHolder);
                if (account != null) {
                    new Transactions().deposit(account, amoutForDep);
                    JOptionPane.showMessageDialog(this, "Deposit successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    previousFrame.getDatabase().updateDatabase(previousFrame.getBank().getAccounts(), previousFrame.getBank().getNumAccounts());
                    previousFrame.getDatabase().save();
                } else {
                    JOptionPane.showMessageDialog(this, "No such account found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid amount format.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if(actionEvent.getSource() == exit){
            this.dispose();
        }
    }
}