package ma.enset.bankapp.dtos;

import lombok.Data;
import ma.enset.bankapp.enums.OperationType;

import java.util.Date;

@Data
public class OperationDto {
    private Long id;
    private Date date;
    private double amount;
    private OperationType operationType;
    private String description;
}
