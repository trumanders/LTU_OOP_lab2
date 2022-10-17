package andjox1;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * This class defines the bank accounts. It is the abstract base
 * class of SavingsAccounts and CreditAccoints
 * @author Anders Johansson, andjox-1
 */
public abstract class Account {
    private static int countingAllAccountNumbers = 1000;
    private int accountNumber;
    protected BigDecimal balance = new BigDecimal("0");
    protected ArrayList<String> transactions = new ArrayList<>();

    /* Constructor */
    public Account() {
        countingAllAccountNumbers++;
        accountNumber = countingAllAccountNumbers;
    }
    public abstract BigDecimal getInterestRate();
    public abstract String getAccountType();
    public abstract boolean makeWithdrawal(int amount);

    public void makeDeposit(int amount) {
        balance = balance.add(new BigDecimal(amount));
        saveTransaction(amount);
    }


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
        return this.balance;
    }


    /**
     * Gets the account number of the last added account
     * @return int  The account number of the last added account
     */
    public static int getCountingAllAccountNumbers() {
        return countingAllAccountNumbers;
    }


    /**
     * Saves the information about a transfer transactions made on the account
     * @param amount    The amount that is being withdrawn or deposited
     */
    protected void saveTransaction(int amount) {
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = time.format(new Date());
        String formattedAmount = NumberFormat.getCurrencyInstance(new Locale("sv","SE")).format(amount);
        String formattedBalance = NumberFormat.getCurrencyInstance(new Locale("sv", "SE")).format(balance);
        transactions.add(currentTime + " " + formattedAmount + " Saldo: " + formattedBalance);
    }
 }
