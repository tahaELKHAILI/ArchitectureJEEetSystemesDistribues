package ma.enset.bankapp.dtos;

import lombok.Data;
import ma.enset.bankapp.entities.Customer;
import ma.enset.bankapp.enums.AccountStatus;

import java.util.Date;

@Data
public class SavingAccountDto extends BankAccountDto {
    private String id;
    private Date createdAt;
    private double balance;
    private AccountStatus accountStatus;
    private CustomerDto customerDto;
    private double interestRate;
}
