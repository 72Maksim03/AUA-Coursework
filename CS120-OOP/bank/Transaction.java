package bank;

import java.time.LocalDateTime;

public class Transaction {
    private double amount;
    private LocalDateTime timestamp;
    private TransactionType type;

    public enum TransactionType {
        DEPOSIT,
        WITHDRAWAL
    }

    public Transaction(double amount) {
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
        this.type = amount >= 0 ? TransactionType.DEPOSIT : TransactionType.WITHDRAWAL;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public TransactionType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", timestamp=" + timestamp +
                ", type=" + type +
                '}';
    }

}