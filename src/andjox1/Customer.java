package andjox1;

import java.util.ArrayList;

/**
 * This class defines the customers and performs actions on the accounts. Set customer name, create accounts,
 * delete accounts, add savings account, add credit account.
 * Interest, and search for a customer's accounts, and hand out information about a customer's
 * accounts. This class holds the list of a customer's accounts.
 * @author Anders Johansson, andjox-1
 */
public class Customer {
    private String fName;
    private String lName;
    private final String PERSONAL_NUMBER;

    /* Contains a list of all the customer's accounts */
    private ArrayList<Account> allAccounts = new ArrayList<>();


    /* Constructor */
    public Customer(String fName, String lName, String persNum) {
        this.fName = fName;
        this.lName = lName;
        this.PERSONAL_NUMBER = persNum;
    }


    /**
     * Creates a new savings account in the account ArrayList.
     */
    public void addSavingsAccount() {
        this.allAccounts.add(new SavingsAccount());
    }


    /**
     * Creates a new credit account in the account ArrayList.
     */
    public void addCreditAccount() {
        this.allAccounts.add(new CreditAccount());
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
     * Deletes the account that is passed
     * @param account     The account to be deleted.
     */
    public void deleteAccount(Account account) {
        allAccounts.remove(account);
    }


    // GETTERS //
    /**
     * Gets the account at the specified index in the account ArrayList.
     * @param index     The account index in the ArrayList
     * @return Account  Account-object
     */
    public Account getAccount(int index) {
        return allAccounts.get(index);
    }


    /**
     * Gets the personal number for the customer.
     * @return String   The customer's personal number.
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


    /**
     * Gets the total number of accounts for the customer by checking the size of the account ArrayList. This
     * is used to iterate through the account list.
     * @return int  The total number of accounts for the customer.
     */
    public int getNumberOfAccounts() {
        return allAccounts.size();
    }
}
