package andjox1;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * This class defines the customers. It has methods to make deposit, withdrawal, calculate
 * interest, and search for a customer's accounts, and hand out information about a customer's
 * accounts.
 * @author Anders Johansson, andjox-1
 */
public class Customer {
    private String fName;
    private String lName;
    private final String PERSONAL_NUMBER;

    /* Contains a list of the customer's credit accounts */
    private ArrayList<CreditAccount> creditAccounts = new ArrayList<>();

    /* Contains a list of the customer's savings accounts */
    private ArrayList<SavingsAccount> savingsAccounts = new ArrayList<>();

    public Customer(String fName, String lName, String persNum) {
        this.fName = fName;
        this.lName = lName;
        this.PERSONAL_NUMBER = persNum;
    }


    /**
     * Creates a new savings account
     */
    public void addSavingsAccount() {
        this.savingsAccounts.add(new SavingsAccount());
    }


    /**
     * Creates a new credit account
     */
    public void addCreditAccount() {
        this.creditAccounts.add(new CreditAccount());
    }



    /**
     * Sets the customer's first name
     * @param fName     The customer's first name
     */
    public void setfName(String fName) {
        this.fName = fName;
    }

    /**
     * Sets the customer's last name
     * @param lName     The customer's last name
     */
    public void setlName(String lName) {
        this.lName = lName;
    }


    /**
     * Deletes the account with the specified index in the account-ArrayList
     * @param index     The index of the account to be deleted
     */
    public void deleteSavingsAccount(int index) {
        savingsAccounts.remove(index);
    }

    public void deleteCreditAccount(int index) {
        creditAccounts.remove(index);
    }


    // GETTERS //

    /**
     * Get the savings account at the specified index
     * @param index     The account index in the ArrayList
     * @return          SavingsAccount-object
     */
    public SavingsAccount getSavingsAccount(int index) {
        return savingsAccounts.get(index);
    }

    /**
     * Get the credit account at the specified index
     * @param index     The account index in the ArrayList
     * @return          CreditAccount-object
     */
    public CreditAccount getCreditAccount(int index) {
        return creditAccounts.get(index);
    }

    /**
     * Get the customer's personal number
     * @return String   The customer's personal number
     */
    public String getPERSONAL_NUMBER() {
        return this.PERSONAL_NUMBER;
    }

    /**
     * Gets the customer's first name
     * @return String   The customer's first name
     */
    public String getfName() {
        return this.fName;
    }

    /**
     * Gets the customer's last name
     * @return String   The customer's last name
     */
    public String getlName() {
        return this.lName;
    }

    /**
     * Gets the customers first and last name.
     * @return String   The customer's first and last name separated by space.
     */
    public String getFullName() {
        return getfName() + " " + getlName();
    }

    public int getNumberOfSavingsAccounts() {
        return savingsAccounts.size();
    }

    public int getNumberOfCreditAccounts() {
        return creditAccounts.size();
    }


    /**
     * Gets the balance of the savings account at the specified index
     * @return BigDecimal   The balance of the account
     */
    public BigDecimal getSavingsAccountBalance(int index) {
        return savingsAccounts.get(index).getBalance();
    }


    /**
     * Gets the balance of the credit account at the specified index
     * @return BigDecimal   The balance of the account
     */
    public BigDecimal getCreditAccountBalance(int index) {
        return creditAccounts.get(index).getBalance();
    }


    /**
     * Checks if an account exists and gets it's index in the account ArrayList
     * @param accountNum    The account number to look up
     * @return int          The index of the account in the account ArrayList. Returns -1 if the account was not found
     */
    public int searchForCustomerAccount(int accountNum) {
        int accountIndex = -1;
        for (Account account : savingsAccounts) {
            accountIndex++;
            if (account.getAccountNumber() == accountNum) {
                return accountIndex;
            }
        }
        return -1;
    }
}
