package andjox1;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SavingsAccount extends Account {
    private final BigDecimal INTEREST_RATE = new BigDecimal("1.2");
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

    public static BigDecimal getWithdrawalFee() {
        return WITHDRAWAL_FEE;
    }

    public String getAccountType() {
        return ACCOUNT_TYPE;
    }

    public void withdraw(int amount) {
        balance = balance.subtract(new BigDecimal(amount));
        allowFreeDeposit = false;
        saveTransaction(amount * -1);

    }

    public boolean getAllowFreeDeposit() {
        return  allowFreeDeposit;
    }
}
