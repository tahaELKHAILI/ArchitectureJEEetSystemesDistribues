package ma.enset.bankapp.services;

import ma.enset.bankapp.dtos.CustomerDto;
import ma.enset.bankapp.entities.Customer;
import ma.enset.bankapp.exceptions.CustomerNotFoundException;

import java.util.List;

public interface CustomerServiceInterface {

    CustomerDto saveCustomer(CustomerDto customerDto);
    void deleteCustomer(String customerID);
    CustomerDto getCustomer(String customerID) throws CustomerNotFoundException;
    CustomerDto updateCustomer(CustomerDto customerDto);
    List<CustomerDto> getAllCustomers();
    List<CustomerDto> searchCustomers(String keyword);
}
