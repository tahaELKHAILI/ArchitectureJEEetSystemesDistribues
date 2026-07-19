package ma.enset.bankapp.repositories;

import ma.enset.bankapp.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {

    List<Operation> findByBankAccountId(String accountID);
    List<Operation> findByTransactionID(String transactionID);
    Page<Operation> findByBankAccountIdOrderByDateDesc (String accountID, Pageable pageable);
}
