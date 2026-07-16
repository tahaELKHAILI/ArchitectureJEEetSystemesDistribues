package ma.enset.bankapp.dtos;

import lombok.Data;

@Data
public class TransactionDto {
    private String transactionID;
    private String accountID;
    private double amount;
    private String description;
}
