package andjox1;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * This class handles all of the customers and has methods to extract
 * information from customer  object. It has methods to error check
 * the input and also get information about the accounts indirectly.
 * @author Anders Johansson, andjox-1
 */
public class BankLogic {
    private ArrayList<Customer> allCustomers = new ArrayList<>();


    /**
     * Create a new savings account for a specified customer
     * @param pNo   The personal number of the customer to create a new account for
     * @return int  The account number of the created account
     */
    public int createSavingsAccount(String pNo) {
        Customer customer = getCustomerByPersonalNumber(pNo);
        if (customer == null) {
            return -1;
        }
        customer.addSavingsAccount();
        return Account.getCountingAllAccountNumbers();
    }


    /**
     * Create a new credit account for a specified customer
     * @param pNo   The personal number of the customer to create a new account for
     * @return int  The account number of the created account
     */
    public int createCreditAccount(String pNo) {
        Customer customer = getCustomerByPersonalNumber(pNo);
        if (customer == null) {
            return -1;
        }
        customer.addCreditAccount();
        return Account.getCountingAllAccountNumbers();
    }


    /**
     * Gets a list of all transactions made on the account.
     * The list contains information about date, time, amount, balance after the transaction.
     * Return empty list of there are no transactions made on the account.
     * Return null of the customer or account doesn't exist.
     * @param pNo       The personal number of the customer.
     * @param accountId The account number
     * @return ArrayList<String>    The list of account info.
     */
    public ArrayList<String> getTransactions(String pNo, int accountId) {
        ArrayList<String> transactionInfo = new ArrayList<>();

        return transactionInfo;
    }

    /**
     * Returns a String ArrayList with all customers' persNum and full name.
     */
    public ArrayList<String> getAllCustomers() {
        ArrayList<String> allCustomersPersNumAndName = new ArrayList<>();
        for (Customer customer : allCustomers) {
            allCustomersPersNumAndName.add(customer.getPERSONAL_NUMBER() + " " + customer.getFullName());
        }
        return allCustomersPersNumAndName;
    }

    /**
     * Creates a new customer object. Checks if personal number is unique.
     * @param name      Customer's first name
     * @param surname   Customer's last name
     * @param pNo       Customer's personal number
     */
    public boolean createCustomer(String name, String surname, String pNo) {
        // Check if personal number IS VALID (does not already exists)
        if (isPersonalNumberValid(pNo)) {

            /* If personal number is valid, create the customer and add to the ArrayList */
            allCustomers.add(new Customer(name, surname, pNo));
            return true;
        }
        return false;
    }


    /**
     * Retuns an ArrayList containing the customer's name, personal number, and it's accounts
     * and information about the customer's accounts.
     * @param pNo   Customer's personal number
     * @return      Info about the customer
     */
    public ArrayList<String> getCustomer(String pNo) {

        /* Search for the customer using the personal number pNo */
        Customer customer = getCustomerByPersonalNumber(pNo);
        if (customer == null) {
            return null;
        }

        /* ArrayList to hold customer info to be returned */
        ArrayList<String> customerInfo = new ArrayList<>();

        /* Add customer name as the first element of the customerInfo */
        customerInfo.add(pNo + " " + customer.getFullName());

        /* Then add the information about each account. */
        /* Iterate through savings accounts */
        for (int i = 0; i < customer.getNumberOfSavingsAccounts(); i++) {
            String accountNumber = Integer.toString(customer.getSavingsAccount(i).getAccountNumber());
            String formatBalance = formatMoneyString(customer.getSavingsAccountBalance(i));
            String formatInterestRate = formatPercentString(customer.getSavingsAccount(i).getInterestRate());
            customerInfo.add(accountNumber + " " + formatBalance + " " + customer.getSavingsAccount(i).getAccountType() + " " + formatInterestRate);
        }

        /* Iterate through credit accounts */
        for (int i = 0; i < customer.getNumberOfCreditAccounts(); i++) {
            String accountNumber = Integer.toString(customer.getCreditAccount(i).getAccountNumber());
            String formatBalance = formatMoneyString(customer.getCreditAccountBalance(i));
            String formatInterestRate = formatPercentString(customer.getCreditAccount(i).getInterestRate());
            customerInfo.add(accountNumber + " " + formatBalance + " " + customer.getCreditAccount(i).getAccountType() + " " + formatInterestRate);
        }
        return customerInfo;
    }


    /**
     * Method to change a customer's name
     * @param name      The customer's new first name
     * @param surname   The customer's new last name
     * @param pNo       The pesonal number of the customer to change it's name
     * @return boolean  false: if both strings passed are empty, or if the customer was not found
     *                  true: if the operation was successful
     */
    public boolean changeCustomerName(String name, String surname, String pNo) {
        if (name.length() == 0 && surname.length() == 0) {
            return false;
        }
        /* Search for the customer to change name */
        Customer customer = getCustomerByPersonalNumber(pNo);
        if (customer == null) {
            return false;
        }
        if (name.length() > 0) customer.setfName(name);
        if (surname.length() > 0) customer.setlName(surname);
        return true;
    }


    /**
     * Gets information about a specific account: account number, balance, account type, interest rate
     * @param pNo           The personal number of the customer in possession of the account
     * @param accountId     The accont number for the account to get information about
     * @return String       A string containing the information, return null if the customer or acccount was not found
     */
    public String getAccount(String pNo, int accountId) {

        //KOLLA IGENOM BÅDE SAVINGS OCH CREDIT. METODEN SOM SÖKER SKA SÖKA IGENOM BÅDA KONTOTYPER och kolla efter kontotyp
        /* Personal number exists? */
        Customer customer = getCustomerByPersonalNumber(pNo);
        if (customer == null) {
            return null;
        }

        /* Search through all the customers accounts */
        Account account = getAccountByCustomer(customer, accountId);

        /* If account doesn't exist for that customer */
        if (account == null) {
            return null;
        }

        String accountType = account.getAccountType();
        String accountNumber = Integer.toString(account.getAccountNumber());
        String balance = formatMoneyString(account.getBalance());
        String interestRate = formatPercentString(account.getInterestRate());
        return accountNumber + " " + balance + " " + accountType + " " + interestRate;
    }


    /**
     * Performs a depoit to the specified account
     * @param pNo       The personal number of the customer
     * @param accountId The account number to make a deposit to
     * @param amount    The amount of money to transfer to the account
     * @return boolean  Returns true of the amount was > 0, both customer and it's account was found, and the deposit was made.
     */
    public boolean deposit(String pNo, int accountId, int amount) {
        Customer customer = getCustomerByPersonalNumber(pNo);
        if (amount < 0 || customer == null) {
            return false;
        }
        Account account = getAccountByCustomer(customer, accountId);
        if (account == null) {
            return false;
        }

        /* Make deposit */
        account.deposit(amount);
        return true;
    }


    /**
     * Makes a withdrawal from an account
     * @param pNo       The customer's personal number
     * @param accountId The account number to make the withdrawal from
     * @param amount    The amount of money to withdraw
     * @return boolean  Returns true of the amount was > 0, both customer and it's account was found, and the withdrawal was made.
     */
    public boolean withdraw(String pNo, int accountId, int amount) {
        Customer customer = getCustomerByPersonalNumber(pNo);
        if (customer == null) {
            return false;
        }
        Account account = getAccountByCustomer(customer, accountId);
        if (amount < 0 || account == null) {
            return false;
        }

        /* Check if there is enough money on the account */
        if (account.getBalance().compareTo(new BigDecimal(amount)) >= 0) {
            account.withdraw(amount);
            return true;
        }
        return false;
    }


    /**
     * Closes an account and returns information abount the closed account
     * @param pNo       The personal number of the customer
     * @param accountId The account number for the account to be closed
     * @return String   Returns Accont number, balance, account tyoe, and interest before it is closed
     */
    public String closeAccount(String pNo, int accountId) {
        Customer customer = getCustomerByPersonalNumber(pNo);
        if (customer == null) {
            return null;
        }

        Account account = getAccountByCustomer(customer, accountId);

        /* Get account info */
        String deletedAccountInfo = getDeletedAccountInfo(account);

        /* Delete the account */ // SAVINGS OR CREDIT? METODEN SOM SÖKER SKA SÖKA IGENOM BÅDA KONTOTYPER
        allCustomers.remove(account);
        return deletedAccountInfo;
    }


    /**
     * Removes a customer and it's accounts
     * @param pNo           The customers personal number
     * @return ArrayList    A list of information about the deleted customer and it's accounts
     */
    public ArrayList<String> deleteCustomer(String pNo) {
        Customer customer = getCustomerByPersonalNumber(pNo);

        /* If personal number not found, return null, else start collecting info */
        if (customer == null) {
            return null;
        }
        ArrayList<String> deletedCustomerInfo = new ArrayList<>();

        /* Add personal number and name as the first element */
        deletedCustomerInfo.add(pNo + " " + customer.getFullName());

        /* Add information about each SAVINGS account in the following elements of the ArrayList */
        for (int i = 0; i < customer.getNumberOfSavingsAccounts(); i++) {

            /* For each account, add the information */
            deletedCustomerInfo.add(getDeletedAccountInfo(customer.getSavingsAccount(i)));
        }

        /* Add information about each CREDIT account in the following elements of the ArrayList */
        for (int i = 0; i < customer.getNumberOfCreditAccounts(); i++) {

            /* For each account, add the information */
            deletedCustomerInfo.add(getDeletedAccountInfo(customer.getCreditAccount(i)));
        }

        /* Delete customer savings accounts */
        for (int i = 0; i < customer.getNumberOfSavingsAccounts(); i++) {
            customer.deleteSavingsAccount(i);
        }

        /* Delete customer credit accounts */
        for (int i = 0; i < customer.getNumberOfCreditAccounts(); i++) {
            customer.deleteCreditAccount(i);
        }
        /* Delete customer */
        allCustomers.remove(getCustomerByPersonalNumber(pNo));
        return deletedCustomerInfo;
    }


    // CUSTOM METHODS //
    /**
     * Checks if the personal number of a new customer is valid
     * @param pNo       The personal number to be validated
     * @return boolean  Returns true if the personal number was valid (didn't alreay exist), else returns false
     */
    private boolean isPersonalNumberValid(String pNo)
    {
        boolean isValid = true;
        for (Customer customer : allCustomers)
        {
            if (customer.getPERSONAL_NUMBER().equals(pNo)) {
                return false;
            }
        }
        return true;
    }


    /**
     * Looks for a customer
     * @param pNo       The personal number to search for
     * @return int      The index of the customer in the customer ArrayList. Returns -1 if the customer was not found
     */
    private Customer getCustomerByPersonalNumber(String pNo) {
        for (Customer customer : allCustomers) {
            if (customer.getPERSONAL_NUMBER().equals(pNo)) {
                return customer;
            }
        }
        return null;
    }


    private Account getAccountByCustomer(Customer customer, int accountNumber) {

        /* Savings acounts */
        for (int i = 0; i < customer.getNumberOfSavingsAccounts(); i++) {
            if (customer.getSavingsAccount(i).getAccountNumber() == accountNumber) {
                return customer.getSavingsAccount(i);
            }
        }

        for (int i = 0; i < customer.getNumberOfCreditAccounts(); i++) {
            if (customer.getCreditAccount(i).getAccountNumber() == accountNumber) {
                return customer.getCreditAccount(i);
            }
        }
        return null;
    }


    /**
     * Formats a BigDecimal number as swedish money
     * @param money     The BigDecimal number to be formatted
     * @return String   The BigDecimal as a String
     */
    private String formatMoneyString(BigDecimal money) {
        return NumberFormat.getCurrencyInstance(new Locale("sv","SE")).format(money);
    }


    /* Format the output of the interest rate */
    private String formatPercentString(BigDecimal interestRate) {
        NumberFormat percentFormat = NumberFormat.getPercentInstance(new Locale("sv", "SE"));
        percentFormat.setMaximumFractionDigits(1);
        return percentFormat.format(interestRate.divide(new BigDecimal("100")));
    }


    /**
     * Gets information about an account to be deleted
     * @param account   The account to get info from
     * @return String   Account number, balance, account type, and interest
     */
    private String getDeletedAccountInfo(Account account) {
        BigDecimal interest = account.getBalance().multiply(account.getInterestRate().divide(new BigDecimal("100")));

        String balance = account.getBalance().toString();
        String interestStr = NumberFormat.getCurrencyInstance(new Locale("sv","SE")).format(interest);
        return account.getAccountNumber() + " " + balance + " " + account.getAccountType() + " " + interestStr;
    }
}
