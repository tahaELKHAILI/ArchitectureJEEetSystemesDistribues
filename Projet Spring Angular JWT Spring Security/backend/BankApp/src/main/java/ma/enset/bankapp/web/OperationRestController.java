package ma.enset.bankapp.web;

import lombok.AllArgsConstructor;
import ma.enset.bankapp.dtos.AccountHistoryDto;
import ma.enset.bankapp.dtos.OperationDto;
import ma.enset.bankapp.dtos.TransactionDto;
import ma.enset.bankapp.dtos.TransferRequestDto;
import ma.enset.bankapp.exceptions.AccountNotFoundException;
import ma.enset.bankapp.exceptions.InsuficiantBalanceException;
import ma.enset.bankapp.services.OperationServiceImplementation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class OperationRestController {
    private OperationServiceImplementation operationServiceImplementation;

    @PostMapping("/accounts/credit")
    public TransactionDto credit(@RequestBody TransactionDto transactionDto) throws AccountNotFoundException {
        operationServiceImplementation.credit(
                transactionDto.getTransactionID(),
                transactionDto.getAccountID(),
                transactionDto.getAmount(),
                transactionDto.getDescription());
        return transactionDto;
    }

    @PostMapping("accounts/debit")
    public TransactionDto debit(@RequestBody TransactionDto transactionDto) throws InsuficiantBalanceException, AccountNotFoundException {
        operationServiceImplementation.debit(
                transactionDto.getTransactionID(),
                transactionDto.getAccountID(),
                transactionDto.getAmount(),
                transactionDto.getDescription());
        return transactionDto;
    }

    @PostMapping("/accounts/transfer")
    public void transfer(@RequestBody TransferRequestDto transferRequestDto) throws InsuficiantBalanceException, AccountNotFoundException {
        operationServiceImplementation.transfer(
                transferRequestDto.getSourceAccountID(),
                transferRequestDto.getDestinationAccountID(),
                transferRequestDto.getAmount()
        );
    }

    @PostMapping("/accounts/transfer/cancel/{transactionID}")
    public void cancelTransfer(@PathVariable(name = "transactionID") String transactionID) throws InsuficiantBalanceException, AccountNotFoundException {
        operationServiceImplementation.cancelTransfer(transactionID);
    }

    @GetMapping("accounts/transactions/{accountID}")
    public List<OperationDto> accountTransactions(@PathVariable(name = "accountID") String accountID){
        return operationServiceImplementation.accountHistory(accountID);
    }

    @GetMapping("accounts/transactions/{accountID}/")
    public AccountHistoryDto getAccountHistory(@PathVariable(name = "accountID") String accountID,
                                                     @RequestParam(name = "page", defaultValue = "0") int page,
                                                     @RequestParam(name = "size", defaultValue = "5") int size) throws AccountNotFoundException {
        return operationServiceImplementation.getAccountHistory(accountID, page, size);
    }
}
