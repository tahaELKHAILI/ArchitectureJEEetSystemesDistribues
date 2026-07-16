package ma.enset.bankapp.dtos;

import lombok.Data;

@Data
public class TransferRequestDto {
    private String sourceAccountID;
    private String destinationAccountID;
    private double amount;
}
