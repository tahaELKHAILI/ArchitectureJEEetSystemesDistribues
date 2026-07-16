package ma.enset.bankapp.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.enset.bankapp.dtos.BankAccountDto;
import ma.enset.bankapp.dtos.CurrentAccountDto;
import ma.enset.bankapp.dtos.SavingAccountDto;
import ma.enset.bankapp.dtos.UpdateAccountRequestDto;
import ma.enset.bankapp.entities.BankAccount;
import ma.enset.bankapp.entities.CurrentAccount;
import ma.enset.bankapp.entities.Customer;
import ma.enset.bankapp.entities.SavingAccount;
import ma.enset.bankapp.enums.AccountStatus;
import ma.enset.bankapp.exceptions.AccountNotFoundException;
import ma.enset.bankapp.exceptions.CustomerNotFoundException;
import ma.enset.bankapp.mappers.AppMappers;
import ma.enset.bankapp.repositories.BankAccountRepository;
import ma.enset.bankapp.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class BankAccountServiceImplementation implements BankAccountServiceInterface {
    private BankAccountRepository bankAccountRepository;
    private CustomerRepository customerRepository;
    private AppMappers mappers;


    @Override
    public List<BankAccountDto> getAllBankAccounts() {
        List<BankAccount> bankAccounts = bankAccountRepository.findAll();
        List<BankAccountDto> bankAccountsDto = bankAccounts.stream().map(bankAccount ->{
            if(bankAccount instanceof CurrentAccount){
                CurrentAccount currentAccount = (CurrentAccount) bankAccount;
                return mappers.fromAccountToDto(currentAccount);
            }
            else{
                SavingAccount savingAccount = (SavingAccount) bankAccount;
                return mappers.fromAccountToDto(savingAccount);
            }
        }).collect(Collectors.toList());
        return bankAccountsDto;
    }

    @Override
    public BankAccountDto getBankAccount(String accountID) throws AccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findById(accountID).orElseThrow(() ->
                new AccountNotFoundException("Account not found"));
        if(bankAccount instanceof CurrentAccount){
            CurrentAccount currentAccount = (CurrentAccount) bankAccount;
            return mappers.fromAccountToDto(currentAccount);
        }
        else{
            SavingAccount savingAccount = (SavingAccount) bankAccount;
            return mappers.fromAccountToDto(savingAccount);
        }
    }


    @Override
    public CurrentAccountDto createCurrentAccount(double initialBalance, double overdraft, String customerID) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerID).orElseThrow(()->
                new CustomerNotFoundException("Customer not found"));

        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setCustomer(customer);
        currentAccount.setBalance(initialBalance);
        currentAccount.setOverdraft(overdraft);
        currentAccount.setCreatedAt(new Date());
        currentAccount.setAccountStatus(AccountStatus.CREATED);
        bankAccountRepository.save(currentAccount);

        CurrentAccountDto newAccountDto = mappers.fromAccountToDto(currentAccount);
        return newAccountDto;
    }

    @Override
    public CurrentAccountDto updateCurrentAccount(UpdateAccountRequestDto updateAccountRequestDto, String accountID) throws AccountNotFoundException {
        CurrentAccount bankAccount = (CurrentAccount) bankAccountRepository.findById(accountID).orElseThrow(()->
                new AccountNotFoundException("Account not found"));
        bankAccount.setBalance(updateAccountRequestDto.getBalance());
        bankAccount.setOverdraft(updateAccountRequestDto.getOverdraft());
        bankAccount.setAccountStatus(updateAccountRequestDto.getAccountStatus());
        bankAccountRepository.save(bankAccount);
        CurrentAccountDto updatedAccount = mappers.fromAccountToDto(bankAccount);
        return updatedAccount;
    }

    @Override
    public SavingAccountDto createSavingAccount(double initialBalance, double interestRate, String customerID) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerID).orElseThrow(()->
                new CustomerNotFoundException("Customer not found"));

        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setCustomer(customer);
        savingAccount.setCreatedAt(new Date());
        savingAccount.setBalance(initialBalance);
        savingAccount.setInterestRate(interestRate);
        savingAccount.setAccountStatus(AccountStatus.CREATED);
        bankAccountRepository.save(savingAccount);

        SavingAccountDto newAccountDto = mappers.fromAccountToDto(savingAccount);
        return newAccountDto;
    }

    @Override
    public SavingAccountDto updateSavingAccount(UpdateAccountRequestDto updateAccountRequestDto, String accountID) throws AccountNotFoundException {
        SavingAccount bankAccount = (SavingAccount) bankAccountRepository.findById(accountID).orElseThrow(()->
                new AccountNotFoundException("Account not found"));
        bankAccount.setBalance(updateAccountRequestDto.getBalance());
        bankAccount.setInterestRate(updateAccountRequestDto.getInterestRate());
        bankAccount.setAccountStatus(updateAccountRequestDto.getAccountStatus());
        bankAccountRepository.save(bankAccount);
        SavingAccountDto updatedAccount = mappers.fromAccountToDto(bankAccount);
        return updatedAccount;
    }

    @Override
    public void deleteAccount(String accountID) {
        bankAccountRepository.deleteById(accountID);
    }
}
