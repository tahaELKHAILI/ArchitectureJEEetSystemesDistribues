package ma.enset.bankapp.services;

import ma.enset.bankapp.dtos.OperationDto;
import ma.enset.bankapp.entities.Operation;
import ma.enset.bankapp.exceptions.AccountNotFoundException;
import ma.enset.bankapp.exceptions.InsuficiantBalanceException;

import java.util.List;

public interface OperationServiceInterface {

    void debit(String accountID, long amount, String description) throws AccountNotFoundException, InsuficiantBalanceException;
    void credit(String accountID, long amount, String description)throws AccountNotFoundException;
    void transfer(String sourceAccountID, String destinationAccountID, long amount)throws AccountNotFoundException, InsuficiantBalanceException;
    List<OperationDto> accountHistory(String accountID);

}
