package andjox1;

import java.math.BigDecimal;

public class CreditAccount extends Account {
    private final static BigDecimal DEBT_INTEREST_RATE = new BigDecimal("0.07");
    private final static BigDecimal SAVINGS_INTEREST_RATE = new BigDecimal("0.005");
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
    }


    public BigDecimal calculateInterest() {

        return new BigDecimal("0");
    }


    /* GETTERS */

    public BigDecimal getInterestRate() {
        if (getBalance().compareTo(new BigDecimal("0")) > 0) {
            interestRate = SAVINGS_INTEREST_RATE;
        } else {
            interestRate = DEBT_INTEREST_RATE;
        }
        return interestRate;
    }


    public String getAccountType() {
        return ACCOUNT_TYPE;
    }
}
