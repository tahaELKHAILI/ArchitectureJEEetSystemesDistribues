package ma.enset.bankapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Data
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String email;
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<BankAccount> bankAccounts;
}
