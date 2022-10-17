package andjox1;

import java.math.BigDecimal;

/**
 * This class inherits from Account, and defines the savings accounts.
 * @author Anders Johansson, andjox-1
 */
public class SavingsAccount extends Account {
    private final BigDecimal INTEREST_RATE = new BigDecimal("1.2");
    private final static BigDecimal WITHDRAWAL_FEE = new BigDecimal("2");
    private final static String ACCOUNT_TYPE = "Sparkonto";
    private boolean allowFreeWithdrawal = true;


    /* Constructor*/
    public SavingsAccount() {
        super();
        allowFreeWithdrawal = true;
    }


    /**
     * Makes a withdrawal transaction according to the
     * rules for withdrawal on the account type savings account. Saves the
     * transaction.
     * @param amount    The amount to withdraw from the savings account
     * @return          Boolean - true for successful withdrawal, else returns false.
     */
    @Override
    public boolean makeWithdrawal(int amount) {

        /* Convert amount to BigDecimal */
        BigDecimal bigDecAmount = new BigDecimal(amount);
        BigDecimal fee = new BigDecimal("0");
        if (!allowFreeWithdrawal) {

            /* Fee = amount * (withdrawal_percent / 100) */
            fee = new BigDecimal(amount).multiply(WITHDRAWAL_FEE.divide(new BigDecimal("100")));
        }

        /* If not enough money (If balance minus amount and fee is less than zero */
        if (balance.subtract(bigDecAmount.add(fee)).compareTo(new BigDecimal("0")) < 0) {
            return false;
        }

        bigDecAmount = bigDecAmount.add(fee);
        balance = balance.subtract(bigDecAmount);

        /* Make the withdrawn amount negative to be saved as a withdrawal transaction */
        saveTransaction(bigDecAmount.intValue() * -1);

        allowFreeWithdrawal = false;
        return true;
    }


    /**
     * Gets the account interest rate, which is 1.2 % for savings acounts.
     * @return final BigDecimal     The interest rate in percent, for the account.
     */
    public BigDecimal getInterestRate() {
        return INTEREST_RATE;
    }


    /**
     * Gets the account type for savings account
     * @return final String     The account type.
     */
    public String getAccountType() {
        return ACCOUNT_TYPE;
    }
}
