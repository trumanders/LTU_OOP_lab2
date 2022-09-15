package andjox1;

import java.math.BigDecimal;

/**
 * This class defines the bank accounts.
 * @author Anders Johansson, andjox-1
 */
public abstract class Account {
    private static int countingAllAccountNumbers = 1000;
    private int accountNumber;
    protected BigDecimal balance = new BigDecimal("0");


    /* Constructor */
    public Account() {
        countingAllAccountNumbers++;
        accountNumber = countingAllAccountNumbers;
    }


    /* TRANSACTIONS */

    /**
     * Calculates the new balance after a deposit
     * @param amount    The amount of money to deposit
     */
    public void deposit(int amount) {
        balance = balance.add(new BigDecimal(amount));
    }

    public abstract void withdraw(int amount);
    public abstract BigDecimal getInterestRate();
    public abstract BigDecimal calculateInterest();
    public abstract String getAccountType();

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
     * Gets the account number of the last added account
     * @return int  The account number of the last added account
     */
    public static int getCountingAllAccountNumbers() {
        return countingAllAccountNumbers;
    }
 }
