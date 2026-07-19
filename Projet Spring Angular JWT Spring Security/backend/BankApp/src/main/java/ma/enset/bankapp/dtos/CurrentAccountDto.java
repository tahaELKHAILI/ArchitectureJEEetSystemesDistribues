package ma.enset.bankapp.dtos;

import lombok.Data;
import ma.enset.bankapp.enums.AccountStatus;

import java.util.Date;

@Data
public class CurrentAccountDto extends BankAccountDto {

    private double overdraft;
}
