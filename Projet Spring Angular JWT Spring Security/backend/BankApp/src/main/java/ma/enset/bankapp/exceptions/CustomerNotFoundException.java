package ma.enset.bankapp.exceptions;

public class CustomerNotFoundException extends Exception {

    public CustomerNotFoundException(String message){
        super(message);
    }
}
