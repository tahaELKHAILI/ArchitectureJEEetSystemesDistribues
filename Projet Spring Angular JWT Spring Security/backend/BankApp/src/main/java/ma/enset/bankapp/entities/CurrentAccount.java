package ma.enset.bankapp.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Data
@DiscriminatorValue("CA")
public class CurrentAccount extends BankAccount{
    private double overdraft;
}
