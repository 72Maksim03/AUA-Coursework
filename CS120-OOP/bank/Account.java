package bank;

import database.MalformedAccountException;

/**
 * This class represents a bank account.
 */
public class Account {
    private String accNumber;
    private String accHolder;
    private String pinCode;
    private double balance;
    private TransactionHistory transactionsHistory;

    /**
     * This constructor constructs a new account object with given information
     *
     * @param accNumber The account number
     * @param accHolder The account holder name and surname
     * @param pinCode   The pin code of the account
     * @param balance   The initial balance of the account
     */
    public Account(String accNumber, String accHolder, String pinCode, double balance) {
        this.accNumber = accNumber;
        this.accHolder = accHolder;
        this.pinCode = pinCode;
        this.balance = balance;
        this.transactionsHistory = new TransactionHistory();
    }

    /**
     * This constructor constructs a new account object with given information
     *
     * @param info given information
     */
    public Account(String info) throws MalformedAccountException {
        String[] in = info.split("_");
        new Account(in[0], in[1], in[2], Double.parseDouble(in[3]));
    }

    /**
     * This constructor creates a new account with randomly generated account number
     *
     * @param accHolder The account holder name and surname
     * @param pinCode   The pin code of the account
     */
    public Account(String accHolder, String pinCode) {
        this.accNumber = AccNumber.createRandomNumber();
        this.accHolder = accHolder;
        this.pinCode = pinCode;
        this.balance = 0.0;
        this.transactionsHistory = new TransactionHistory();
    }

    public Account(Account other) {
        this.accNumber = other.getAccNumber();
        this.accHolder = other.getAccHolder();
        this.pinCode = other.getPinCode();
        this.balance = other.getBalance();
        this.transactionsHistory = other.getTransactionsHistory();
    }

    /**
     * Gets the account number
     *
     * @return The account number
     */
    public String getAccNumber() {
        return this.accNumber;
    }

    /**
     * Gets the account holder name and surname
     *
     * @return The account holder name and surname
     */
    public String getAccHolder() {
        return this.accHolder;
    }

    /**
     * Gets the account PIN code
     *
     * @return The PIN code
     */
    public String getPinCode() {
        return this.pinCode;
    }

    /**
     * Validates the entered PIN code.
     *
     * @param enteredPin The PIN code entered by the user.
     * @return true if the entered PIN code matches the account's PIN code, otherwise false.
     */
    public boolean validatePin(String enteredPin) {
        return this.pinCode.equals(enteredPin);
    }

    /**
     * Sets a new PIN code for the account.
     *
     * @param oldPinCode The old PIN code for verification.
     * @param newPinCode The new PIN code to set.
     */
    public void setNewPinCode(String oldPinCode, String newPinCode) {
        if (validatePin(oldPinCode))
            this.pinCode = newPinCode;
    }

    /**
     * Gets the current balance of the account.
     *
     * @return The balance of the account.
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * Sets the current balance of the account.
     *
     * @param balance New balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public TransactionHistory getTransactionsHistory() {
        return transactionsHistory;
    }

    /**
     * @return a string representation of account
     */
    public String toString(){
        return this.accNumber + "_" + this.accHolder + "_" + this.pinCode + "_" + this.balance;
    }
}