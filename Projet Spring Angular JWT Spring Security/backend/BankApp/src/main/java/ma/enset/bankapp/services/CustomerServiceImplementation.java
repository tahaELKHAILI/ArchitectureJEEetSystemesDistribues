package ma.enset.bankapp.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.enset.bankapp.dtos.CustomerDto;
import ma.enset.bankapp.entities.Customer;
import ma.enset.bankapp.exceptions.CustomerNotFoundException;
import ma.enset.bankapp.mappers.AppMappers;
import ma.enset.bankapp.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class CustomerServiceImplementation implements CustomerServiceInterface {
    private CustomerRepository customerRepository;
    private AppMappers mappers;


    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        Customer newCustomer = mappers.fromDtoToCustomer(customerDto);
        customerRepository.save(newCustomer);
        return mappers.fromCustomerToDto(newCustomer);
    }

    @Override
    public void deleteCustomer(String customerID) {
        customerRepository.deleteById(customerID);
    }

    @Override
    public CustomerDto getCustomer(String customerID) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerID).orElseThrow(()
                -> new CustomerNotFoundException("Customer not found"));
        return mappers.fromCustomerToDto(customer);
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        Customer customer = mappers.fromDtoToCustomer(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        return mappers.fromCustomerToDto(savedCustomer);
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDto> customersDto = customers.stream().map(customer ->
                mappers.fromCustomerToDto(customer)).collect(Collectors.toList());
        return customersDto;
    }

    @Override
    public List<CustomerDto> searchCustomers(String keyword) {
        List<Customer> customers = customerRepository.searchCustomer(keyword);
        List<CustomerDto> customersDto = customers.stream().map(customer ->
                mappers.fromCustomerToDto(customer)).collect(Collectors.toList());
        return customersDto;
    }
}
