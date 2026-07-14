package ma.enset.bankapp.mappers;

import ma.enset.bankapp.dtos.CurrentAccountDto;
import ma.enset.bankapp.dtos.CustomerDto;
import ma.enset.bankapp.dtos.OperationDto;
import ma.enset.bankapp.dtos.SavingAccountDto;
import ma.enset.bankapp.entities.CurrentAccount;
import ma.enset.bankapp.entities.Customer;
import ma.enset.bankapp.entities.Operation;
import ma.enset.bankapp.entities.SavingAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AppMappers {

    //Customer
    CustomerDto fromCustomerToDto(Customer customer){
        CustomerDto customerDto = new CustomerDto();
        BeanUtils.copyProperties(customer, customerDto);
        return customerDto;
    }

    Customer fromDtoToCustomer(CustomerDto customerDto){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);
        return customer;
    }

    // Current account
    CurrentAccountDto fromAccountToDto(CurrentAccount currentAccount){
        CurrentAccountDto accountDto = new CurrentAccountDto();
        BeanUtils.copyProperties(currentAccount, accountDto);
        accountDto.setCustomerDto(fromCustomerToDto(currentAccount.getCustomer()));
        accountDto.setType("Current");
        return accountDto;
    }

    CurrentAccount fromDtoToAccount(CurrentAccountDto currentAccountDto){
        CurrentAccount account = new CurrentAccount();
        BeanUtils.copyProperties(currentAccountDto, account);
        account.setCustomer(fromDtoToCustomer(currentAccountDto.getCustomerDto()));
        return account;
    }

    // Saving account
    SavingAccountDto fromAccountToDto(SavingAccount savingAccount){
        SavingAccountDto accountDto = new SavingAccountDto();
        BeanUtils.copyProperties(savingAccount, accountDto);
        accountDto.setCustomerDto(fromCustomerToDto(savingAccount.getCustomer()));
        accountDto.setType("Saving");
        return accountDto;
    }

    SavingAccount fromDtoToAccount(SavingAccountDto savingAccountDto){
        SavingAccount account = new SavingAccount();
        BeanUtils.copyProperties(savingAccountDto, account);
        account.setCustomer(fromDtoToCustomer(savingAccountDto.getCustomerDto()));
        return account;
    }

    // Operation
    OperationDto fromDtoToOperation(Operation operation){
        OperationDto operationDto = new OperationDto();
        BeanUtils.copyProperties(operation, operationDto);
        return operationDto;
    }
}
