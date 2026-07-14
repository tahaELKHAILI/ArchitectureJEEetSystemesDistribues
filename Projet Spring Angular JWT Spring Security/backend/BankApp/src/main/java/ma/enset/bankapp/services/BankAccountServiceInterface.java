package ma.enset.bankapp.services;

import ma.enset.bankapp.entities.*;
import ma.enset.bankapp.exceptions.AccountNotFoundException;
import ma.enset.bankapp.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountServiceInterface {

    //Bank account methods
    List<BankAccount> getAllBankAccounts();
    BankAccount getBankAccount(String accountID) throws AccountNotFoundException;



    //Current account methods
    CurrentAccount createCurrentAccount(double initialBalance, double overdraft, String customerID) throws CustomerNotFoundException;

    //Saving account methods
    SavingAccount createSavingAccount(double initialBalance, double interestRate, String customerID) throws CustomerNotFoundException;

}
