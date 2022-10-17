package andjox1;

import java.math.BigDecimal;

/**
 * This class inherits from Account and defines credit accounts.
 * @author Anders Johansson, andjox-1
 */
public class CreditAccount extends Account {
    private final static BigDecimal DEBT_INTEREST_RATE = new BigDecimal("7");
    private final static BigDecimal SAVINGS_INTEREST_RATE = new BigDecimal("0.5");
    private BigDecimal interestRate;
    private final static int CREDIT_LIMIT = -5000;
    private final String ACCOUNT_TYPE = "Kreditkonto";


    /**
     * Gets the interest rate based on the state of the balance. Positive money gives 0.5% positive interest.
     * Negative money gives 7% negative interest.
     * @return BigDecimal   The correct interest rate based on the balance
     */
    public BigDecimal getInterestRate() {
        if (balance.compareTo(new BigDecimal("0")) >= 0) {
            interestRate = SAVINGS_INTEREST_RATE;
        } else {
            interestRate = DEBT_INTEREST_RATE;
        }
        return interestRate;
    }

    /**
     * Makes a withdrawal according to the rules for withdrawal on the
     * account type credit account. Saves the transaction.
     * @param amount    The amount to be withdrawn from the account
     * @return          Boolean. True for successful transaction, else returns false;
     */
    public boolean makeWithdrawal(int amount) {

        /* Check credit limit */
        if ((balance.subtract(new BigDecimal(amount))).compareTo((new BigDecimal(CREDIT_LIMIT))) < 0)
            return false;

        balance = balance.subtract(new BigDecimal(amount));
        saveTransaction(amount * -1);
        return true;
    }


    /**
     * Gets the account type
     * @return String   The account type
     */
    public String getAccountType() {
        return ACCOUNT_TYPE;
    }
}
