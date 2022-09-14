package andjox1;

import javax.swing.plaf.basic.BasicButtonUI;
import java.math.BigDecimal;

public class SavingsAccount extends Account {
    private final static BigDecimal INTEREST_RATE = new BigDecimal(".012");
    private final static BigDecimal WITHDRAWAL_FEE = new BigDecimal("0.02");
    private final static String ACCOUNT_TYPE = "Savings account";
    private static int numberOfAccounts = 0;
    private boolean allowFreeDeposit;


    public SavingsAccount() {
        super();
        allowFreeDeposit = true;
        numberOfAccounts++;
    }


    public int getNumberOfAccounts() {
        return numberOfAccounts;
    }

    @Override
    public void withdraw(int amount) {
        if (allowFreeDeposit) {
            withdraw(amount);
            allowFreeDeposit = false;
        }
        amount += WITHDRAWAL_FEE.multiply(new BigDecimal(amount)).intValue();
    }

    /**
     * Gets the interest rate that all savings accounts have (static)
     * @return BigDecimal   The interest that all accounts have
     */
    public BigDecimal getInterestRate() {
        return INTEREST_RATE;
    }
}
