package ui;

import bank.Bank;
import database.AccountDatabase;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JframeMain extends JFrame implements ActionListener {
    private JLabel heading;
    private Bank bank;
    private AccountDatabase database;
    public Bank getBank(){
        return this.bank;
    }
    public AccountDatabase getDatabase(){return this.database;}
    private JButton BtnForCreating, BtnForCheckingB, BtnForDeposit, BtnForWithdraw, BtnForTransfer, BtnForDeleting, BtnForExiting;
    public JframeMain(){
        database = new AccountDatabase();
        bank = new Bank(database);
        setTitle("BANK MANAGMENT SYSTEM");
        heading = new JLabel("WELCOME");
        heading.setFont(new Font("Osward", Font.BOLD, 38));
        heading.setBounds(200,40,300,40);
        add(heading);

        BtnForCreating = new JButton("Create new account");
        BtnForCreating.setBackground(Color.BLACK);
        BtnForCreating.setForeground(Color.WHITE);
        BtnForCreating.setFont(new Font("Arial", Font.BOLD, 14));
        BtnForCreating.setBounds(150,100,300,30);
        add(BtnForCreating);
        BtnForCreating.addActionListener(this);

        BtnForCheckingB = new JButton("Check the balance");
        BtnForCheckingB.setBackground(Color.BLACK);
        BtnForCheckingB.setForeground(Color.WHITE);
        BtnForCheckingB.setFont(new Font("Arial", Font.BOLD, 14));
        BtnForCheckingB.setBounds(150,150,300,30);
        add(BtnForCheckingB);
        BtnForCheckingB.addActionListener(this);

        BtnForDeposit = new JButton("Deposit");
        BtnForDeposit.setBackground(Color.BLACK);
        BtnForDeposit.setForeground(Color.WHITE);
        BtnForDeposit.setFont(new Font("Arial", Font.BOLD, 14));
        BtnForDeposit.setBounds(150,200,300,30);
        add(BtnForDeposit);
        BtnForDeposit.addActionListener(this);

        BtnForWithdraw = new JButton("Withdraw");
        BtnForWithdraw.setBackground(Color.BLACK);
        BtnForWithdraw.setForeground(Color.WHITE);
        BtnForWithdraw.setFont(new Font("Arial", Font.BOLD, 14));
        BtnForWithdraw.setBounds(150,250,300,30);
        add(BtnForWithdraw);
        BtnForWithdraw.addActionListener(this);

        BtnForTransfer = new JButton("Transfer");
        BtnForTransfer.setBackground(Color.BLACK);
        BtnForTransfer.setForeground(Color.WHITE);
        BtnForTransfer.setFont(new Font("Arial", Font.BOLD, 14));
        BtnForTransfer.setBounds(150,300,300,30);
        add(BtnForTransfer);
        BtnForTransfer.addActionListener(this);

        BtnForDeleting = new JButton("Delete an account");
        BtnForDeleting.setBackground(Color.BLACK);
        BtnForDeleting.setForeground(Color.WHITE);
        BtnForDeleting.setFont(new Font("Arial", Font.BOLD, 14));
        BtnForDeleting.setBounds(150,350,300,30);
        add(BtnForDeleting);
        BtnForDeleting.addActionListener(this);

        BtnForExiting = new JButton("Exit");
        BtnForExiting.setBackground(Color.BLACK);
        BtnForExiting.setForeground(Color.WHITE);
        BtnForExiting.setFont(new Font("Arial", Font.BOLD, 14));
        BtnForExiting.setBounds(150,400,300,30);
        add(BtnForExiting);
        BtnForExiting.addActionListener(this);

        setLayout(null);

        getContentPane().setBackground(Color.WHITE);

        setSize(600,600);
        setLocation(400,100);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource() == BtnForCreating){
            Creating frame1 = new Creating(this);
            frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame1.setSize(600, 600);
            frame1.setLocationRelativeTo(null);
            frame1.setVisible(true);
        } else if(actionEvent.getSource() == BtnForCheckingB){
            CheckingBalance frame2 = new CheckingBalance(this);
            frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame2.setSize(600, 600);
            frame2.setLocationRelativeTo(null);
            frame2.setVisible(true);
        } else if(actionEvent.getSource() == BtnForDeposit){
            Deposit frame3 = new Deposit(this);
            frame3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame3.setSize(600, 600);
            frame3.setLocationRelativeTo(null);
            frame3.setVisible(true);
        } else if(actionEvent.getSource() == BtnForWithdraw){
            Withdraw frame4 = new Withdraw(this);
            frame4.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame4.setSize(600, 600);
            frame4.setLocationRelativeTo(null);
            frame4.setVisible(true);
        } else if(actionEvent.getSource() == BtnForTransfer){
            Transaction frame5 = new Transaction(this);
            frame5.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame5.setSize(600, 600);
            frame5.setLocationRelativeTo(null);
            frame5.setVisible(true);
        } else if(actionEvent.getSource() == BtnForDeleting){
            Remove frame6 = new Remove(this);
            frame6.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame6.setSize(600, 600);
            frame6.setLocationRelativeTo(null);
            frame6.setVisible(true);
        } else if(actionEvent.getSource() == BtnForExiting){
            System.exit(0);
        }
    }
}
