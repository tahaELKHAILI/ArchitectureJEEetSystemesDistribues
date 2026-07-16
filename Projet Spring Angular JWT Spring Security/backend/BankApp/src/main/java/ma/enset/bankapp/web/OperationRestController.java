package ma.enset.bankapp.web;

import lombok.AllArgsConstructor;
import ma.enset.bankapp.dtos.TransactionDto;
import ma.enset.bankapp.dtos.TransferRequestDto;
import ma.enset.bankapp.exceptions.AccountNotFoundException;
import ma.enset.bankapp.exceptions.InsuficiantBalanceException;
import ma.enset.bankapp.services.OperationServiceImplementation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class OperationRestController {
    private OperationServiceImplementation operationServiceImplementation;

    @PostMapping("/accounts/credit")
    public TransactionDto credit(@RequestBody TransactionDto transactionDto) throws AccountNotFoundException {
        operationServiceImplementation.credit(
                transactionDto.getAccountID(),
                transactionDto.getAmount(),
                transactionDto.getDescription());
        return transactionDto;
    }

    @PostMapping("accounts/debit")
    public TransactionDto debit(@RequestBody TransactionDto transactionDto) throws InsuficiantBalanceException, AccountNotFoundException {
        operationServiceImplementation.debit(
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
}
