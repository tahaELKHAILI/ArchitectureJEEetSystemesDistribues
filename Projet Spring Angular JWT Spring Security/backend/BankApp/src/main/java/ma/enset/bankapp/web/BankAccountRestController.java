package ma.enset.bankapp.web;

import lombok.AllArgsConstructor;
import ma.enset.bankapp.dtos.*;
import ma.enset.bankapp.exceptions.AccountNotFoundException;
import ma.enset.bankapp.exceptions.CustomerNotFoundException;
import ma.enset.bankapp.services.BankAccountServiceImplementation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class BankAccountRestController {
    private BankAccountServiceImplementation bankAccountServiceImplementation;


    @GetMapping("/accounts")
    @PreAuthorize("hasAnyAuthority('SCOPE_USER', 'SCOPE_ADMIN')")
    public List<BankAccountDto> getAllAccounts(){
        return bankAccountServiceImplementation.getAllBankAccounts();
    }

    @GetMapping("/accounts/{accountID}")
    @PreAuthorize("hasAnyAuthority('SCOPE_USER', 'SCOPE_ADMIN')")
    public BankAccountDto getAccount(@PathVariable(name = "accountID") String accountID) throws AccountNotFoundException {
        return bankAccountServiceImplementation.getBankAccount(accountID);
    }

    @GetMapping("/accounts/customer/{customerID}")
    @PreAuthorize("hasAnyAuthority('SCOPE_USER', 'SCOPE_ADMIN')")
    public List<BankAccountDto> getUsersAccount(@PathVariable(name = "customerID") String customerID){
        return bankAccountServiceImplementation.getBankAccountByCustomerID(customerID);
    }

    @PostMapping("/accounts/current")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public CurrentAccountDto createCurrentAccount(@RequestBody CreateCurrentAccountDto currentAccountDto) throws CustomerNotFoundException {
        return bankAccountServiceImplementation.createCurrentAccount(currentAccountDto.getInitialBalance(),
                currentAccountDto.getOverdraft(),
                currentAccountDto.getCustomerID());
    }

    @PutMapping("/accounts/current/{accountID}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public  CurrentAccountDto updateCurrentAccount(@PathVariable(name = "accountID") String accountID,
                                                   @RequestBody UpdateAccountRequestDto updateAccountRequestDto) throws AccountNotFoundException {
        return bankAccountServiceImplementation.updateCurrentAccount(updateAccountRequestDto, accountID);
    }

    @PostMapping("/accounts/saving")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public SavingAccountDto createSavingAccount(@RequestBody CreateSavingAccountDto createSavingAccountDto) throws CustomerNotFoundException {
        return bankAccountServiceImplementation.createSavingAccount(createSavingAccountDto.getInitialBalance(),
                createSavingAccountDto.getInterestRate(),
                createSavingAccountDto.getCustomerID());
    }

    @PutMapping("/accounts/saving/{accountID}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public  SavingAccountDto updateSavingAccount(@PathVariable(name = "accountID") String accountID,
                                                   @RequestBody UpdateAccountRequestDto updateAccountRequestDto) throws AccountNotFoundException {
        return bankAccountServiceImplementation.updateSavingAccount(updateAccountRequestDto, accountID);
    }

}
