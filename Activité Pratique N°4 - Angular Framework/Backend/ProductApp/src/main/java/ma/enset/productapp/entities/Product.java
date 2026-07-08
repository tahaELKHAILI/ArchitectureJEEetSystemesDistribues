package ma.enset.productapp.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private Long id;
    private String name;
    @JsonProperty("price")
    private double price;
    @JsonProperty("quantity")
    private int quantity;
    private boolean selected;
}
