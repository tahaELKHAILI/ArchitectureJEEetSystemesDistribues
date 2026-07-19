package ma.enset.bankapp.mappers;

import ma.enset.bankapp.dtos.*;
import ma.enset.bankapp.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AppMappers {

    //Customer
    public CustomerDto fromCustomerToDto(Customer customer){
        CustomerDto customerDto = new CustomerDto();
        BeanUtils.copyProperties(customer, customerDto);
        return customerDto;
    }

    public Customer fromDtoToCustomer(CustomerDto customerDto){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);
        return customer;
    }

    //Bank accounts

    public BankAccountDto fromBankAccountToDto(BankAccount bankAccount) {
        if (bankAccount instanceof CurrentAccount) {
            return fromAccountToDto((CurrentAccount) bankAccount);
        } else if (bankAccount instanceof SavingAccount) {
            return fromAccountToDto((SavingAccount) bankAccount);
        }
        return null;
    }

    // Current account
    public CurrentAccountDto fromAccountToDto(CurrentAccount currentAccount){
        CurrentAccountDto accountDto = new CurrentAccountDto();
        BeanUtils.copyProperties(currentAccount, accountDto);
        accountDto.setCustomerDto(fromCustomerToDto(currentAccount.getCustomer()));
        accountDto.setType("Current");
        return accountDto;
    }

    public CurrentAccount fromDtoToAccount(CurrentAccountDto currentAccountDto){
        CurrentAccount account = new CurrentAccount();
        BeanUtils.copyProperties(currentAccountDto, account);
        account.setCustomer(fromDtoToCustomer(currentAccountDto.getCustomerDto()));
        return account;
    }

    // Saving account
    public SavingAccountDto fromAccountToDto(SavingAccount savingAccount){
        SavingAccountDto accountDto = new SavingAccountDto();
        BeanUtils.copyProperties(savingAccount, accountDto);
        accountDto.setCustomerDto(fromCustomerToDto(savingAccount.getCustomer()));
        accountDto.setType("Saving");
        return accountDto;
    }

    public SavingAccount fromDtoToAccount(SavingAccountDto savingAccountDto){
        SavingAccount account = new SavingAccount();
        BeanUtils.copyProperties(savingAccountDto, account);
        account.setCustomer(fromDtoToCustomer(savingAccountDto.getCustomerDto()));
        return account;
    }

    // Operation
    public OperationDto fromOperationToDto(Operation operation){
        OperationDto operationDto = new OperationDto();
        BeanUtils.copyProperties(operation, operationDto);
        return operationDto;
    }
}
