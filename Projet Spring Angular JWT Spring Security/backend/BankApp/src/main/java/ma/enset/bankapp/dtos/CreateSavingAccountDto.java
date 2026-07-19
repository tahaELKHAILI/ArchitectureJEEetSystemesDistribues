package ma.enset.bankapp.dtos;

import lombok.Data;

@Data
public class CreateSavingAccountDto {
    private double initialBalance;
    private double interestRate;
    private String customerID;
}
