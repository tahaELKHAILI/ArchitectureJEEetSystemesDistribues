package ma.enset.bankapp.repositories;

import ma.enset.bankapp.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Query("select c from Customer c where c.name like :kw")
    public List<Customer> searchCustomer(@Param("kw") String keyword);
}
