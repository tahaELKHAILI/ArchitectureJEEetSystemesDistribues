package ma.enset.productapp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
@Builder
public class Product {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private int quantity;
    private double price;
}
