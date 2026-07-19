package ma.enset.bankapp.dtos;

import lombok.Data;

@Data
public class CreateCurrentAccountDto {
    private double initialBalance;
    private double overdraft;
    private String customerID;
}
