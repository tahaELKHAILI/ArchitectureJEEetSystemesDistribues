package ma.enset.bankapp.web;

import lombok.AllArgsConstructor;
import ma.enset.bankapp.dtos.*;
import ma.enset.bankapp.exceptions.AccountNotFoundException;
import ma.enset.bankapp.exceptions.CustomerNotFoundException;
import ma.enset.bankapp.services.BankAccountServiceImplementation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class BankAccountRestController {
    private BankAccountServiceImplementation bankAccountServiceImplementation;

    @GetMapping("/accounts")
    public List<BankAccountDto> getAllAccounts(){
        return bankAccountServiceImplementation.getAllBankAccounts();
    }

    @GetMapping("/accounts/{accountID}")
    public BankAccountDto getAccount(@PathVariable(name = "accountID") String accountID) throws AccountNotFoundException {
        return bankAccountServiceImplementation.getBankAccount(accountID);
    }

    @GetMapping("/accounts/customer/{customerID}")
    public List<BankAccountDto> getUsersAccount(@PathVariable(name = "customerID") String customerID){
        return bankAccountServiceImplementation.getBankAccountByCustomerID(customerID);
    }

    @PostMapping("/accounts/current")
    public CurrentAccountDto createCurrentAccount(@RequestBody CreateCurrentAccountDto currentAccountDto) throws CustomerNotFoundException {
        return bankAccountServiceImplementation.createCurrentAccount(currentAccountDto.getInitialBalance(),
                currentAccountDto.getOverdraft(),
                currentAccountDto.getCustomerID());
    }

    @PutMapping("/accounts/current/{accountID}")
    public  CurrentAccountDto updateCurrentAccount(@PathVariable(name = "accountID") String accountID,
                                                   @RequestBody UpdateAccountRequestDto updateAccountRequestDto) throws AccountNotFoundException {
        return bankAccountServiceImplementation.updateCurrentAccount(updateAccountRequestDto, accountID);
    }

    @PostMapping("/accounts/saving")
    public SavingAccountDto createSavingAccount(@RequestBody CreateSavingAccountDto createSavingAccountDto) throws CustomerNotFoundException {
        return bankAccountServiceImplementation.createSavingAccount(createSavingAccountDto.getInintialBalance(),
                createSavingAccountDto.getInterestRate(),
                createSavingAccountDto.getCustomerID());
    }

    @PutMapping("/accounts/saving/{accountID}")
    public  SavingAccountDto updateSavingAccount(@PathVariable(name = "accountID") String accountID,
                                                   @RequestBody UpdateAccountRequestDto updateAccountRequestDto) throws AccountNotFoundException {
        return bankAccountServiceImplementation.updateSavingAccount(updateAccountRequestDto, accountID);
    }

}
