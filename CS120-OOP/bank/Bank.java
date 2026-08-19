package bank;
import database.AccountDatabase;
import database.MalformedAccountException;

import java.util.ArrayList;

/**
 * Represents a bank that manages accounts.
 */
public class Bank {
    public ArrayList<Account> accounts;
    private int num_accounts;


    public int getNumAccounts(){
        return num_accounts;
    }
    /**
     * Constructs a new Bank object.
     */
    public Bank() {
        accounts = new ArrayList<>();
        num_accounts = 0;
    }

    public Bank(AccountDatabase database) {
        accounts = database.getDatabase();
        num_accounts = database.getNumberOfAccounts();
    }

    /**
     * Retrieves the arraylist of accounts in the bank.
     *
     * @return The arraylist of accounts.
     */
    public ArrayList<Account> getAccounts() {
        return this.accounts;
    }

    /**
     * Finds and returns the account with the specified account number.
     *
     * @param accNumber The account number to search for.
     * @return The account with the specified account number, or null if not found.
     */
    public Account findAccountByAccNumber(String accNumber) {
        for (int i = 0; i < num_accounts; i++) {
            if (accounts.get(i).getAccNumber().equals(accNumber)) {
                return accounts.get(i);
            }
        }
        return null;
    }



    /**
     * Finds and returns the account of the specified account holder.
     *
     * @param accHolder The name of the account holder.
     * @return The account of the specified account holder, or null if not found.
     */
    public Account findAccountByAccHolder(String accHolder) {
        for (int i = 0; i < num_accounts; i++) {
            if (accounts.get(i).getAccHolder().equals(accHolder)) {
                return accounts.get(i);
            }
        }
        return null;
    }

    /**
     * Adds an account to the bank.
     *
     * @param account The account to add.
     */
    public void addAccount(Account account) {
        accounts.add(account);
        num_accounts++;

    }

    /**
     * Removes an account from the bank if the pin code provided matches the account's pin code.
     *
     * @param account The account to be removed.
     * @param pinCode The pin code of the account.
     */
    public void removeAccount(Account account, String pinCode) {
        if (account == null) {
            System.out.println("Invalid account: account is null.");
            return;
        }
        for (int i = 0; i < num_accounts; i++) {
            if (accounts.get(i).getAccNumber().equals(account.getAccNumber())) {
                if (accounts.get(i).validatePin(pinCode)) {
                    accounts.remove(i);
                    num_accounts--;
                    System.out.println("Account deleted");
                    return;
                } else{
                    System.out.println("Invalid pin code");
                    return;
                }
            }
        }
        System.out.println("No such account");
    }
}