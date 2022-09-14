package andjox1;

import java.math.BigDecimal;

public class CreditAccount extends Account {

    private final static String ACCOUNT_TYPE = "Credit account";
    private final BigDecimal DEBT_INTEREST_RATE = new BigDecimal("0.07");
    private final BigDecimal SAVINGS_INTEREST_RATE = new BigDecimal("0.005");
    private final int CREDIT_LIMIT = -5000;
    private static int numberOfAccounts = 0;
    public CreditAccount() {
        super();
        numberOfAccounts++;
    }

    public int getNumberOfAccounts() {
        return numberOfAccounts;
    }

}
