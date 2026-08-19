package ui;

import bank.Account;
import bank.Transactions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Transaction extends JFrame implements ActionListener {
    private JframeMain previousFrame;
    private JLabel heading, accHolder, accHolder2, amount;
    private JTextField textFieldHolder, textFieldHolder2, amountToTrans;
    private JButton Transfer, exit;

    public Transaction(JframeMain previousFrame) {
        this.previousFrame = previousFrame;
        setTitle("BANK MANAGMENT SYSTEM");
        heading = new JLabel("Transfer");
        heading.setFont(new Font("Osward", Font.BOLD, 38));
        heading.setBounds(200,40,300,40);
        add(heading);

        accHolder = new JLabel("Account number to transfer from");
        accHolder.setFont(new Font("Osward", Font.BOLD, 14));
        accHolder.setBounds(50, 100, 200, 30);
        add(accHolder);

        textFieldHolder = new JTextField(40);
        textFieldHolder.setBounds(300, 100, 250, 30);
        textFieldHolder.setFont(new Font("Arial", Font.BOLD, 14));
        add(textFieldHolder);

        accHolder2 = new JLabel("Account number to transfer to");
        accHolder2.setFont(new Font("Osward", Font.BOLD, 14));
        accHolder2.setBounds(50, 150, 200, 30);
        add(accHolder2);

        textFieldHolder2 = new JTextField(40);
        textFieldHolder2.setBounds(300, 150, 250, 30);
        textFieldHolder2.setFont(new Font("Arial", Font.BOLD, 14));
        add(textFieldHolder2);

        amount = new JLabel("Amount to transfer");
        amount.setFont(new Font("Osward", Font.BOLD, 14));
        amount.setBounds(50, 200, 200, 30);
        add(amount);

        amountToTrans = new JTextField(40);
        amountToTrans.setBounds(300, 200, 250, 30);
        amountToTrans.setFont(new Font("Arial", Font.BOLD, 14));
        add(amountToTrans);

        Transfer = new JButton("Transfer");
        Transfer.setBackground(Color.BLACK);
        Transfer.setForeground(Color.WHITE);
        Transfer.setFont(new Font("Arial", Font.BOLD, 14));
        Transfer.setBounds(150, 250, 300, 30);
        add(Transfer);
        Transfer.addActionListener(this);

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
        if (actionEvent.getSource() == Transfer) {
            String accNumberFrom = textFieldHolder.getText();
            String accNumberTo = textFieldHolder2.getText();
            double amountToTransfer;
            try {
                amountToTransfer = Double.parseDouble(amountToTrans.getText());
                Account accountFrom = previousFrame.getBank().findAccountByAccNumber(accNumberFrom);
                Account accountTo = previousFrame.getBank().findAccountByAccNumber(accNumberTo);
                if (accountFrom != null && accountTo != null) {
                    if(accountFrom.getBalance() >= amountToTransfer){
                        new Transactions().transfer(accountFrom, accountTo, amountToTransfer);
                        JOptionPane.showMessageDialog(this, "Transfer successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        previousFrame.getDatabase().updateDatabase(previousFrame.getBank().getAccounts(), previousFrame.getBank().getNumAccounts());
                        previousFrame.getDatabase().save();
                    } else{
                        JOptionPane.showMessageDialog(this, "Insufficient balance.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid account number(s).", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid amount format.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if(actionEvent.getSource() == exit){
            this.dispose();
        }
    }
}
