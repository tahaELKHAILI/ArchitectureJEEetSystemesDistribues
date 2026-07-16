package ma.enset.bankapp.services;

import ma.enset.bankapp.dtos.BankAccountDto;
import ma.enset.bankapp.dtos.CurrentAccountDto;
import ma.enset.bankapp.dtos.SavingAccountDto;
import ma.enset.bankapp.dtos.UpdateAccountRequestDto;
import ma.enset.bankapp.exceptions.AccountNotFoundException;
import ma.enset.bankapp.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountServiceInterface {

    //Bank account methods
    List<BankAccountDto> getAllBankAccounts();
    BankAccountDto getBankAccount(String accountID) throws AccountNotFoundException;
    void deleteAccount(String accountID);


    //Current account methods
    CurrentAccountDto createCurrentAccount(double initialBalance, double overdraft, String customerID) throws CustomerNotFoundException;
    CurrentAccountDto updateCurrentAccount(UpdateAccountRequestDto updateAccountRequestDto, String accountID) throws AccountNotFoundException;
    //Saving account methods
    SavingAccountDto createSavingAccount(double initialBalance, double interestRate, String customerID) throws CustomerNotFoundException;
    SavingAccountDto updateSavingAccount(UpdateAccountRequestDto updateAccountRequestDto, String accountID) throws AccountNotFoundException;
}
