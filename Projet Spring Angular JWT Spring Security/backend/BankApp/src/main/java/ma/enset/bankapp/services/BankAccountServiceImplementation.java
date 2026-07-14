package ma.enset.bankapp.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.enset.bankapp.entities.BankAccount;
import ma.enset.bankapp.entities.CurrentAccount;
import ma.enset.bankapp.entities.Customer;
import ma.enset.bankapp.entities.SavingAccount;
import ma.enset.bankapp.exceptions.AccountNotFoundException;
import ma.enset.bankapp.exceptions.CustomerNotFoundException;
import ma.enset.bankapp.repositories.BankAccountRepository;
import ma.enset.bankapp.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class BankAccountServiceImplementation implements BankAccountServiceInterface {
    private BankAccountRepository bankAccountRepository;
    private CustomerRepository customerRepository;


    @Override
    public List<BankAccount> getAllBankAccounts() {
        List<BankAccount> bankAccounts = bankAccountRepository.findAll();
        return bankAccounts;
    }

    @Override
    public BankAccount getBankAccount(String accountID) throws AccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findById(accountID).orElseThrow(() ->
                new AccountNotFoundException("Account not found"));
        return bankAccount;
    }


    @Override
    public CurrentAccount createCurrentAccount(double initialBalance, double overdraft, String customerID) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerID).orElseThrow(()->
                new CustomerNotFoundException("Customer not found"));

        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setCustomer(customer);
        currentAccount.setBalance(initialBalance);
        currentAccount.setOverdraft(overdraft);
        currentAccount.setCreatedAt(new Date());
        return currentAccount;
    }

    @Override
    public SavingAccount createSavingAccount(double initialBalance, double interestRate, String customerID) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerID).orElseThrow(()->
                new CustomerNotFoundException("Customer not found"));

        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setCustomer(customer);
        savingAccount.setCreatedAt(new Date());
        savingAccount.setBalance(initialBalance);
        savingAccount.setInterestRate(interestRate);
        return savingAccount;
    }
}
