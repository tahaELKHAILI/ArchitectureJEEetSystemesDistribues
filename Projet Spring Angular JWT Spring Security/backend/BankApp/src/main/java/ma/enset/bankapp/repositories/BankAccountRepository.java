package ma.enset.bankapp.repositories;

import ma.enset.bankapp.dtos.BankAccountDto;
import ma.enset.bankapp.entities.BankAccount;
import ma.enset.bankapp.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
    List<BankAccount> getBankAccountsByCustomer_Id(String customerID);
}
