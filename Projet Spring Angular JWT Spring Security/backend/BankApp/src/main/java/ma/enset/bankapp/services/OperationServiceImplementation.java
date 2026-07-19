package ma.enset.bankapp.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.enset.bankapp.dtos.AccountHistoryDto;
import ma.enset.bankapp.dtos.OperationDto;
import ma.enset.bankapp.entities.BankAccount;
import ma.enset.bankapp.entities.Operation;
import ma.enset.bankapp.enums.OperationType;
import ma.enset.bankapp.exceptions.AccountNotFoundException;
import ma.enset.bankapp.exceptions.InsuficiantBalanceException;
import ma.enset.bankapp.mappers.AppMappers;
import ma.enset.bankapp.repositories.BankAccountRepository;
import ma.enset.bankapp.repositories.OperationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class OperationServiceImplementation implements OperationServiceInterface{
    private OperationRepository operationRepository;
    private BankAccountRepository bankAccountRepository;
    private AppMappers mappers;

    @Override
    public void debit(String transactionID,String accountID, double amount, String description) throws AccountNotFoundException, InsuficiantBalanceException {
        BankAccount bankAccount = bankAccountRepository.findById(accountID).orElseThrow(()->
                new AccountNotFoundException("Account not found"));

        if(bankAccount.getBalance() < amount)
            throw new InsuficiantBalanceException("Insuficiant funds");

        Operation operation = new Operation();
        operation.setTransactionID(transactionID);
        operation.setOperationType(OperationType.DEBIT);
        operation.setAmount(amount);
        operation.setDescription(description);
        operation.setBankAccount(bankAccount);
        operation.setDate(new Date());
        operationRepository.save(operation);

        bankAccount.setBalance(bankAccount.getBalance()-amount);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void credit(String transactionID, String accountID, double amount, String description) throws AccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findById(accountID).orElseThrow(()->
                new AccountNotFoundException("Account not found"));

        Operation operation = new Operation();
        operation.setTransactionID(transactionID);
        operation.setOperationType(OperationType.CREDIT);
        operation.setAmount(amount);
        operation.setDescription(description);
        operation.setBankAccount(bankAccount);
        operation.setDate(new Date());
        operationRepository.save(operation);

        bankAccount.setBalance(bankAccount.getBalance()+amount);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void transfer(String sourceAccountID, String destinationAccountID, double amount) throws AccountNotFoundException, InsuficiantBalanceException {
        String transactionID = UUID.randomUUID().toString();
        //Source account
        debit(transactionID, sourceAccountID, amount, "Transfer to "+destinationAccountID);
        //Destination account
        credit(transactionID, destinationAccountID, amount, "Transfer from "+sourceAccountID);
    }

    @Override
    public void cancelTransfer(String transactionID) throws AccountNotFoundException, InsuficiantBalanceException {

        List<Operation> operations = operationRepository.findByTransactionID(transactionID);

        Operation debitOperation = operations.stream()
                .filter(op -> op.getOperationType() == OperationType.DEBIT)
                .findFirst()
                .orElse(null);

        Operation creditOperation = operations.stream()
                .filter(op -> op.getOperationType() == OperationType.CREDIT)
                .findFirst()
                .orElse(null);

        String reverseTransactionID = UUID.randomUUID().toString();

        debit(
                reverseTransactionID,
                creditOperation.getBankAccount().getId(),
                creditOperation.getAmount(),
                "Transfer cancellation"
        );

        credit(
                reverseTransactionID,
                debitOperation.getBankAccount().getId(),
                debitOperation.getAmount(),
                "Transfer cancellation"
        );
    }

    @Override
    public List<OperationDto> accountHistory(String accountID) {
        List<Operation> operations = operationRepository.findByBankAccountId(accountID);
        List<OperationDto> operationsDto = operations.stream().map(operation ->
            mappers.fromOperationToDto(operation)
        ).collect(Collectors.toList());
        return operationsDto;
    }

    @Override
    public AccountHistoryDto getAccountHistory(String accountID, int page, int size) throws AccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findById(accountID).orElse(null);
        if(bankAccount == null)
            throw new AccountNotFoundException("Bank account not found");
        Page<Operation> operations = operationRepository.findByBankAccountIdOrderByDateDesc(
                bankAccount.getId(), PageRequest.of(page, size));
        AccountHistoryDto accountHistoryDto = new AccountHistoryDto();

        List<OperationDto> accountOperations = operations.getContent().stream().map(operation ->
                mappers.fromOperationToDto(operation)).collect(Collectors.toList());

        accountHistoryDto.setAccountId(accountID);
        accountHistoryDto.setBalance(bankAccount.getBalance());
        accountHistoryDto.setPageSize(size);
        accountHistoryDto.setCurrentPage(page);
        accountHistoryDto.setTotalPages(operations.getTotalPages());
        accountHistoryDto.setOperationsDTOS(accountOperations);

        return accountHistoryDto;
    }
}
