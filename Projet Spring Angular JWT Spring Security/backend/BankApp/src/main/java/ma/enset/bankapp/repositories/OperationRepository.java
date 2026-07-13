package ma.enset.bankapp.repositories;

import ma.enset.bankapp.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {
}
