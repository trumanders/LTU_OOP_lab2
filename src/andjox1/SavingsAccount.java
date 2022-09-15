package andjox1;

import javax.swing.plaf.basic.BasicButtonUI;
import java.math.BigDecimal;

public class SavingsAccount extends Account {
    private final BigDecimal INTEREST_RATE = new BigDecimal(".012");
    private final static BigDecimal WITHDRAWAL_FEE = new BigDecimal("0.02");
    private final static String ACCOUNT_TYPE = "Sparkonto";
    private boolean allowFreeDeposit;


    public SavingsAccount() {
        super();
        allowFreeDeposit = true;
    }

    public BigDecimal getInterestRate() {
        return INTEREST_RATE;
    }


    public BigDecimal calculateInterest() {
        return new BigDecimal("0");
    }
    public String getAccountType() {
        return ACCOUNT_TYPE;
    }

    @Override
    public void withdraw(int amount) {
        if (allowFreeDeposit) {
            balance = balance.subtract(new BigDecimal(amount));
            allowFreeDeposit = false;
        }
        balance = balance.subtract(WITHDRAWAL_FEE.multiply(new BigDecimal(amount)));
    }

    /**
     * Gets the interest rate that all savings accounts have (static)
     * @return BigDecimal   The interest that all accounts have
     */

}
