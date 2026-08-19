package bank;

/**
 * Represents transactions that can be performed on bank accounts.
 */
public class Transactions {
    /**
     * Deposits the specified amount into the given account.
     *
     * @param account The account to deposit funds into.
     * @param deposit The amount to deposit.
     */
    public void deposit(Account account, double deposit) {
        double newBalance = account.getBalance() + deposit;
        account.setBalance(newBalance);
        Transaction transaction = new Transaction(deposit);
        account.getTransactionsHistory().addTransaction(transaction);
    }

    /**
     * Withdraws the specified amount from the given account.
     *
     * @param account  The account to withdraw funds from.
     * @param withdraw The amount to withdraw.
     * @return True if the withdrawal was successful, false otherwise.
     */
    public boolean withdraw(Account account, double withdraw) {
        if (account.getBalance() >= withdraw) {
            double newBalance = account.getBalance() - withdraw;
            account.setBalance(newBalance);
            Transaction transaction = new Transaction(-withdraw);
            account.getTransactionsHistory().addTransaction(transaction);
            System.out.println("Withdrawal of $" + withdraw + " successful. New balance: $" + account.getBalance());
            return true;
        } else {
            System.out.println("Insufficient funds. Withdrawal of $" + withdraw + " failed.");
            return false;
        }
    }

    /**
     * Transfers funds from one account to another.
     *
     * @param account1 The account to transfer funds from.
     * @param account2 The account to transfer funds to.
     * @param amount   The amount to transfer.
     */
    public void transfer(Account account1, Account account2, double amount) {
        if (withdraw(account1, amount)) {
            deposit(account2, amount);
            System.out.println("Transfer of $" + amount + " successful from " + account1.getAccNumber() + " to " + account2.getAccNumber());
        } else {
            System.out.println("Transfer failed. Insufficient funds in " + account1.getAccNumber());
        }
    }

}