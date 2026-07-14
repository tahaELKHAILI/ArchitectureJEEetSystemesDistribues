package ma.enset.bankapp.repositories;

import ma.enset.bankapp.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {

    List<Operation> findByBankAccountId(String accountID);
}
