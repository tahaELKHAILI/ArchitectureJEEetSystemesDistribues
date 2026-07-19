package ma.enset.bankapp.dtos;

import lombok.Data;

@Data
public class CreateSavingAccountDto {
    private double inintialBalance;
    private double interestRate;
    private String customerID;
}
