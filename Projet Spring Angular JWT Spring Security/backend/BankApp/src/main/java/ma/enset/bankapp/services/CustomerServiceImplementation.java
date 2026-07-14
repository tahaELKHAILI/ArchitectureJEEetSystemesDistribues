package ma.enset.bankapp.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.enset.bankapp.entities.Customer;
import ma.enset.bankapp.exceptions.CustomerNotFoundException;
import ma.enset.bankapp.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CustomerServiceImplementation implements CustomerServiceInterface {
    private CustomerRepository customerRepository;


    @Override
    public Customer saveCustomer(Customer customer) {
        customerRepository.save(customer);
        return customer;
    }

    @Override
    public void deleteCustomer(String customerID) {
        customerRepository.deleteById(customerID);
    }

    @Override
    public Customer getCustomer(String customerID) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerID).orElseThrow(()
                -> new CustomerNotFoundException("Customer not found"));
        return customer;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        customer = customerRepository.save(customer);
        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> searchCustomers(String keyword) {
        List<Customer> customers = customerRepository.searchCustomer(keyword);
        return customers;
    }
}
