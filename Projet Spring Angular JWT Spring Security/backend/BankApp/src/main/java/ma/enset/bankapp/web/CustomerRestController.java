package ma.enset.bankapp.web;

import lombok.AllArgsConstructor;
import ma.enset.bankapp.dtos.CustomerDto;
import ma.enset.bankapp.exceptions.CustomerNotFoundException;
import ma.enset.bankapp.services.CustomerServiceImplementation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class CustomerRestController {
    private CustomerServiceImplementation customerServiceImplementation;

    @GetMapping("/customers")
    public List<CustomerDto> getAllCustomers(){
        return customerServiceImplementation.getAllCustomers();
    }

    @GetMapping("/customers/{id}")
    public CustomerDto getCustomer(@PathVariable(name = "id") String customerID) throws CustomerNotFoundException {
        return customerServiceImplementation.getCustomer(customerID);
    }

    @GetMapping("/customers/search")
    public List<CustomerDto> searchCustomer(@RequestParam(name = "keyword", defaultValue = "") String keyword){
        return customerServiceImplementation.searchCustomers(keyword);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomers(@PathVariable(name = "id") String customerID){
        customerServiceImplementation.deleteCustomer(customerID);
    }

    @PostMapping("/customers")
    public CustomerDto addCustomer(@RequestBody CustomerDto customer){
        return customerServiceImplementation.saveCustomer(customer);
    }

    @PostMapping("/customers/{customerID}")
    public CustomerDto updateCustomer(@PathVariable String customerID , @RequestBody CustomerDto customer){
        return customerServiceImplementation.updateCustomer(customer);
    }
}
