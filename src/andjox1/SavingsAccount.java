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
    private boolean allowFreeDeposit;

    public SavingsAccount() {
        super();
        allowFreeDeposit = true;
    }

    /**
     * Gets the account interest rate, which is 1.2 % for savings acounts.
     * @return final BigDecimal     The interest rate in percent, for the account.
     */
    public BigDecimal getInterestRate() {
        return INTEREST_RATE;
    }

    /**
     * Gets the withdrawal fee for savings accounts, which is 2 %
     * @return
     */
    public static BigDecimal getWithdrawalFee() {
        return WITHDRAWAL_FEE;
    }

    /**
     * Gets the account type for savings account
     * @return final String     The account type.
     */
    public String getAccountType() {
        return ACCOUNT_TYPE;
    }

    /**
     * Performs the withdrawal on the ac    public abstract void withdraw(int amount);
     *     public abstract BigDecimal getInterestRate();
     *     public abstract String getAccountType();count, sets free withdrawal to false and saves the transaction.
     * @param amount    The amount of money to withdraw.
     */
    public void withdraw(int amount) {
        balance = balance.subtract(new BigDecimal(amount));
        allowFreeDeposit = false;
        saveTransaction(amount * -1);

    }

    /**
     * Gets allowFreeDeposit
     * @return boolean  Allow free deposit true or false.
     */
    public boolean getAllowFreeDeposit() {
        return  allowFreeDeposit;
    }
}
