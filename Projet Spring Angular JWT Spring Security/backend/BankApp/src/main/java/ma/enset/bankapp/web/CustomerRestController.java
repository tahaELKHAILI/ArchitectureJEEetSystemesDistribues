package ma.enset.bankapp.web;

import lombok.AllArgsConstructor;
import ma.enset.bankapp.dtos.CustomerDto;
import ma.enset.bankapp.exceptions.CustomerNotFoundException;
import ma.enset.bankapp.services.CustomerServiceImplementation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class CustomerRestController {
    private CustomerServiceImplementation customerServiceImplementation;

    @GetMapping("/customers")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public List<CustomerDto> getAllCustomers(){
        return customerServiceImplementation.getAllCustomers();
    }

    @PreAuthorize("hasAuthority('SCOPE_USER')")
    @GetMapping("/customers/{id}")
    public CustomerDto getCustomer(@PathVariable(name = "id") String customerID) throws CustomerNotFoundException {
        return customerServiceImplementation.getCustomer(customerID);
    }

    @PreAuthorize("hasAuthority('SCOPE_USER')")
    @GetMapping("/customers/search")
    public List<CustomerDto> searchCustomer(@RequestParam(name = "keyword", defaultValue = "") String keyword){
        return customerServiceImplementation.searchCustomers(keyword);
    }

    @DeleteMapping("/customers/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public void deleteCustomers(@PathVariable(name = "id") String customerID){
        customerServiceImplementation.deleteCustomer(customerID);
    }

    @PostMapping("/customers")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public CustomerDto addCustomer(@RequestBody CustomerDto customer){
        return customerServiceImplementation.saveCustomer(customer);
    }

    @PutMapping("/customers/{customerID}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public CustomerDto updateCustomer(@PathVariable String customerID , @RequestBody CustomerDto customer){
        return customerServiceImplementation.updateCustomer(customer);
    }
}
