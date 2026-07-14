package ma.enset.bankapp.services;

import ma.enset.bankapp.entities.Customer;
import ma.enset.bankapp.exceptions.CustomerNotFoundException;

import java.util.List;

public interface CustomerServiceInterface {

    Customer saveCustomer(Customer customer);
    void deleteCustomer(String customerID);
    Customer getCustomer(String customerID) throws CustomerNotFoundException;
    Customer updateCustomer(Customer customer);
    List<Customer> getAllCustomers();
    List<Customer> searchCustomers(String keyword);
}
