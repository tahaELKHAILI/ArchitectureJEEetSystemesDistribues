package ma.enset.bankapp.services;

import ma.enset.bankapp.entities.*;

import java.util.List;

public interface BankServiceInterface {

    //Customer methods
    Customer saveCustomer(Customer customer);
    void deleteCustomer(String customerID);
    Customer getCustomer(String customerID);
    Customer updateCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Customer searchCustomer(String keyword);

    //Bank account methods
    List<BankAccount> getAllBankAccounts();
    BankAccount getBankAccount(String accountID);
    void deleteBankAccount(String accountID);
    void debit(String accountID, long amount, String description);
    void credit(String accountID, long amount, String description);
    void transfer(String sourceAccountID, String destinationAccountID, long amount);
    List<Operation> accountHistory(String customerID);

    //Current account methods
    CurrentAccount createCurrentAccount(double initialBalance, double overdraft, Long customerID);

    //Saving account methods
    SavingAccount createSavingAccount(double initialBalance, double interestRate, Long customerID);

}
