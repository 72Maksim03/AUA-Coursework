package cli;
import bank.Account;
import bank.Bank;
import bank.TransactionHistory;
import bank.Transactions;
import database.AccountDatabase;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BankConsole {
    private Bank bank;
    private Scanner scanner;
    private AccountDatabase database;

    public BankConsole() {
        database = new AccountDatabase();
        bank = new Bank(database);
        scanner = new Scanner(System.in);

    }

    public void start() {

        System.out.println("Welcome to the Bank Console!");
        while (true) {
            try{
                System.out.println("\nSelect an option:");
                System.out.println("1. Create an account");
                System.out.println("2. Check the balance");
                System.out.println("3. Deposit");
                System.out.println("4. Withdraw");
                System.out.println("5. Transfer");
                System.out.println("6. Delete account");
                System.out.println("7. Exit");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        createAccount();
                        break;
                    case 2:
                        checkBalance();
                        break;
                    case 3:
                        deposit();
                        break;
                    case 4:
                        withdraw();
                        break;
                    case 5:
                        transfer();
                        break;
                    case 6:
                        removeAccount();
                        break;
                    case 7:
                        database.save();
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e){
                System.out.println("Please enter the number of your choice");
                scanner.nextLine();
            }

        }
    }

    public void removeAccount(){
        System.out.println("Enter account number:");
        String accNumber = scanner.nextLine();
        Account account = bank.findAccountByAccNumber(accNumber);
        if (account != null) {
            System.out.println("Enter PIN code");
            String pin = scanner.nextLine();
            bank.removeAccount(account, pin);
            database.updateDatabase(bank.getAccounts(), bank.getNumAccounts());
            database.save();
        }
    }

    public void checkBalance(){
        System.out.println("Enter account number:");
        String accNumber = scanner.nextLine();
        Account account = bank.findAccountByAccNumber(accNumber);
        if (account != null) {
            System.out.println("Enter PIN code");
            String pin = scanner.nextLine();
            if(account.validatePin(pin)){
                System.out.println("Balance - " + account.getBalance());
            } else{
                System.out.println("Invalid PIN code");
            }
        } else{
            System.out.println("No such account found");
        }
    }

    private void createAccount() {
        System.out.println("Enter account holder's name:");
        String accHolder = scanner.nextLine();
        System.out.println("Enter PIN code:");
        String pinCode = scanner.nextLine();
        if(pinCode.length() != 4){
            System.out.println("Account is not created. PIN code should be 4 digits, please try again");
            return;
        } else{
            for (int i = 0; i < pinCode.length(); i++) {
                char ch = pinCode.charAt(i);
                if (!Character.isDigit(ch)){
                    System.out.println("Account is not created. PIN code should be 4 digits, please try again");
                    return;
                }
            }
        }
        Account account = new Account(accHolder, pinCode);
        bank.addAccount(account);
        database.updateDatabase(bank.getAccounts(), bank.getNumAccounts());
        database.save();
        System.out.println("Account created successfully with account number: " + account.getAccNumber());
    }

    private void deposit() {
        System.out.println("Enter account number:");
        String accNumber = scanner.nextLine();
        Account account = bank.findAccountByAccNumber(accNumber);
        if (account != null) {
            System.out.println("Enter amount to deposit:");
            double amount = scanner.nextDouble();
            new Transactions().deposit(account, amount);
            database.updateDatabase(bank.getAccounts(), bank.getNumAccounts());
            database.save();
        } else{
            System.out.println("No such account found");
        }
    }

    private void withdraw() {
        System.out.println("Enter account number:");
        String accNumber = scanner.nextLine();
        Account account = bank.findAccountByAccNumber(accNumber);
        if (account != null) {
            System.out.println("Enter amount to withdraw:");
            double amount = scanner.nextDouble();
            new Transactions().withdraw(account, amount);
            database.updateDatabase(bank.getAccounts(), bank.getNumAccounts());
            database.save();
        } else{
            System.out.println("No such account found");
        }
    }

    private void transfer() {
        System.out.println("Enter account number to transfer from:");
        String accNumberFrom = scanner.nextLine();
        Account accountFrom = bank.findAccountByAccNumber(accNumberFrom);
        if (accountFrom != null) {
            System.out.println("Enter account number to transfer to:");
            String accNumberTo = scanner.nextLine();
            Account accountTo = bank.findAccountByAccNumber(accNumberTo);
            if (accountTo != null) {
                System.out.println("Enter amount to transfer:");
                double amount = scanner.nextDouble();
                new Transactions().transfer(accountFrom, accountTo, amount);
                database.updateDatabase(bank.getAccounts(), bank.getNumAccounts());
                database.save();
            } else{
                System.out.println("No such account found");
            }
        } else{
            System.out.println("No such account found");
        }
    }

    public static void main(String[] args) {
        BankConsole bankConsole = new BankConsole();
        bankConsole.start();
    }

}