package bank;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the transaction history of a bank account
 */
public class TransactionHistory {
    private List<Transaction> transactions;

    /**
     * Constructs a new TransactionHistory object
     */
    public TransactionHistory() {
        transactions = new ArrayList<>();
    }

    /**
     * Adds a transaction to the transaction history
     *
     * @param transaction The transaction to add
     */
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    /**
     * Accessor method for the transaction history
     *
     * @return The list of transactions
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }

}