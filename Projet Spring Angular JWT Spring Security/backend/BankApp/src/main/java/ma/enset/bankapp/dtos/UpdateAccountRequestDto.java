package ma.enset.bankapp.dtos;

import lombok.Data;
import ma.enset.bankapp.enums.AccountStatus;

@Data
public class UpdateAccountRequestDto {
    private double balance;
    private double overdraft;
    private double interestRate;
    private AccountStatus accountStatus;
}
