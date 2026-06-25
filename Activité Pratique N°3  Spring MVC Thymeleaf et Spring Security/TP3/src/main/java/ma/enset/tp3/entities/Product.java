package ma.enset.tp3.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder

@Entity
public class Product {
    @Id @GeneratedValue
    private Long id;
    @NotEmpty
    @Size(min = 3, max = 50)
    private String name;
    @Min(1)
    private double quantity;
    @Min(0)
    private  double price;
}
