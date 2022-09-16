package andjox1;

import java.math.BigDecimal;

public class CreditAccount extends Account {
    private final static BigDecimal DEBT_INTEREST_RATE = new BigDecimal("7");
    private final static BigDecimal SAVINGS_INTEREST_RATE = new BigDecimal("0.5");
    private BigDecimal interestRate;
    private final static int CREDIT_LIMIT = -5000;
    private final String ACCOUNT_TYPE = "Kreditkonto";

    public CreditAccount() {
        super();
    }

    /**
     * Calculates the balance after a withdrawal
     * @param amount    The amount of money to withdraw
     */
    public void withdraw(int amount) {
        balance = balance.subtract(new BigDecimal(amount));
        saveTransaction(amount * -1);
    }


    /**
     * Gets the interest rate based on the state of the balance. Positive money gives 0.5% positive interest.
     * Negative money gives 7% negative interest.
     * @return BigDecimal   The correct interest rate based on the balance
     */
    public BigDecimal getInterestRate() {
        if (getBalance().compareTo(new BigDecimal("0")) >= 0) {
            interestRate = SAVINGS_INTEREST_RATE;
        } else {
            interestRate = DEBT_INTEREST_RATE;
        }
        return interestRate;
    }


    /**
     * Gets the account type
     * @return String   The account type
     */
    public String getAccountType() {
        return ACCOUNT_TYPE;
    }

    /**
     * Gets the value of the maximum credit for credit accounts
     * @return int  The credit limit
     */
    public static int getCreditLimit() {
        return CREDIT_LIMIT;
    }
}
