package ma.enset.bankapp.exceptions;

public class InsuficiantBalanceException extends Exception {
    public InsuficiantBalanceException(String message) {
        super(message);
    }
}
