package andjox1;

import java.math.BigDecimal;

/**
 * This class defines the bank accounts.
 * @author Anders Johansson, andjox-1
 */
public abstract class Account {
    private static int countingAllAccountNumbers = 1000;
    private int accountNumber;
    private String accountType;
    private BigDecimal balance = new BigDecimal("0");

    /* Constructor */
    public Account() {
        countingAllAccountNumbers++;
        accountNumber = countingAllAccountNumbers;
    }

    public abstract int getNumberOfAccounts();


    /* TRANSACTIONS */

    /**
     * Calculates the new balance after a deposit
     * @param amount    The amount of money to deposit
     */
    public void deposit(int amount) {
        balance = balance.add(new BigDecimal(amount));
    }

    /**
     * Calculates the balance after a withdrawal
     * @param amount    The amount of money to withdraw
     */
    public void withdraw(int amount) {
        balance = balance.subtract(new BigDecimal(amount));
    }

    /* GETTERS */

    /**
     * Gets the account number
     * @return int  The account number
     */
    public int getAccountNumber() {
        return this.accountNumber;
    }

    /**
     * Gets the balance od the account
     * @return BigDecimal   The account balance
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Gets the account type
     * @return String   The account type
     */
    public String getAccountType() {
        return accountType;
    }


    /**
     * Gets the account number of the last added account
     * @return int  The account number of the last added account
     */
    public static int getCountingAllAccountNumbers() {
        return countingAllAccountNumbers;
    }
 }
