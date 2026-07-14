package ma.enset.bankapp.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.enset.bankapp.dtos.OperationDto;
import ma.enset.bankapp.entities.BankAccount;
import ma.enset.bankapp.entities.Operation;
import ma.enset.bankapp.enums.OperationType;
import ma.enset.bankapp.exceptions.AccountNotFoundException;
import ma.enset.bankapp.exceptions.InsuficiantBalanceException;
import ma.enset.bankapp.mappers.AppMappers;
import ma.enset.bankapp.repositories.BankAccountRepository;
import ma.enset.bankapp.repositories.OperationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class OperationServiceImplementation implements OperationServiceInterface{
    private OperationRepository operationRepository;
    private BankAccountRepository bankAccountRepository;
    private AppMappers mappers;

    @Override
    public void debit(String accountID, long amount, String description) throws AccountNotFoundException, InsuficiantBalanceException {
        BankAccount bankAccount = bankAccountRepository.findById(accountID).orElseThrow(()->
                new AccountNotFoundException("Account not found"));

        if(bankAccount.getBalance() < amount)
            throw new InsuficiantBalanceException("Insuficiant funds");

        Operation operation = new Operation();
        operation.setOperationType(OperationType.DEBIT);
        operation.setAmount(amount);
        operation.setDescription(description);
        operation.setBankAccount(bankAccount);
        operationRepository.save(operation);

        bankAccount.setBalance(bankAccount.getBalance()-amount);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void credit(String accountID, long amount, String description) throws AccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findById(accountID).orElseThrow(()->
                new AccountNotFoundException("Account not found"));

        Operation operation = new Operation();
        operation.setOperationType(OperationType.CREDIT);
        operation.setAmount(amount);
        operation.setDescription(description);
        operation.setBankAccount(bankAccount);
        operationRepository.save(operation);

        bankAccount.setBalance(bankAccount.getBalance()+amount);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void transfer(String sourceAccountID, String destinationAccountID, long amount) throws AccountNotFoundException, InsuficiantBalanceException {
        //Source account
        debit(sourceAccountID, amount, "Transfer to "+destinationAccountID);
        //Destination account
        credit(destinationAccountID, amount, "Transfer from "+sourceAccountID);
    }

    @Override
    public List<OperationDto> accountHistory(String accountID) {
        List<Operation> operations = operationRepository.findByBankAccountId(accountID);
        List<OperationDto> operationsDto = operations.stream().map(operation ->
            mappers.fromOperationToDto(operation)
        ).collect(Collectors.toList());
        return operationsDto;
    }
}
