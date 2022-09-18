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
    private boolean allowFreeWithdrawal;


    /* Constructor*/
    public SavingsAccount() {
        super();
        allowFreeWithdrawal = true;
    }

    public void setAllowFreeWithdrawal(boolean allow) {
        this.allowFreeWithdrawal = allow;
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
     * @return BigDecimal   The withdrawal fee
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
     * Gets allowFreeWithdrawal
     * @return boolean  Allow free deposit true or false.
     */
    public boolean getAllowFreeWithdrawal() {
        return  allowFreeWithdrawal;
    }
}
