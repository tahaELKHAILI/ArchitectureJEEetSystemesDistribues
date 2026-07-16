package ma.enset.bankapp.web;

import lombok.AllArgsConstructor;
import ma.enset.bankapp.dtos.BankAccountDto;
import ma.enset.bankapp.dtos.CurrentAccountDto;
import ma.enset.bankapp.dtos.SavingAccountDto;
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

    @PostMapping("/accounts/current")
    public CurrentAccountDto createCurrentAccount(@RequestBody double inintialBalance, double overdraft, String customerID) throws CustomerNotFoundException {
        return bankAccountServiceImplementation.createCurrentAccount(inintialBalance, overdraft, customerID);
    }


    @PostMapping("/accounts/saving")
    public SavingAccountDto createSavingAccount(@RequestBody double inintialBalance, double interestRate, String customerID) throws CustomerNotFoundException {
        return bankAccountServiceImplementation.createSavingAccount(inintialBalance, interestRate, customerID);
    }
}
