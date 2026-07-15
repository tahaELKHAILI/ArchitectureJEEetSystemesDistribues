package ma.enset.bankapp;

import ma.enset.bankapp.dtos.BankAccountDto;
import ma.enset.bankapp.dtos.CurrentAccountDto;
import ma.enset.bankapp.dtos.CustomerDto;
import ma.enset.bankapp.dtos.SavingAccountDto;
import ma.enset.bankapp.exceptions.CustomerNotFoundException;
import ma.enset.bankapp.services.BankAccountServiceImplementation;
import ma.enset.bankapp.services.CustomerServiceImplementation;
import ma.enset.bankapp.services.OperationServiceImplementation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.stream.Stream;


@SpringBootApplication
public class BankAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAppApplication.class, args);

    }
    @Bean
    CommandLineRunner commandLineRunner(BankAccountServiceImplementation bankAccountServiceImplementation,
                                        CustomerServiceImplementation customerServiceImplementation,
                                        OperationServiceImplementation operationServiceImplementation){
        return args -> {
            //Set customer list
            Stream.of("John", "Jane", "Felix", "Ache", "Smith").forEach(name -> {
                CustomerDto customer = new CustomerDto();
                customer.setName(name);
                customer.setEmail(name+"@gmail.com");
                customerServiceImplementation.saveCustomer(customer);
            });

            //Set bank accounts
            customerServiceImplementation.getAllCustomers().forEach(customer -> {
                try {
                    bankAccountServiceImplementation.createCurrentAccount(Math.random()*120000,9000,customer.getId());
                    bankAccountServiceImplementation.createSavingAccount(Math.random()*90000,2.5, customer.getId());
                } catch (CustomerNotFoundException e) {
                    e.printStackTrace();
                }
            });

            //Set operations
            List<BankAccountDto> accounts = bankAccountServiceImplementation.getAllBankAccounts();
            for(BankAccountDto account: accounts){
                for(int i=0;i<10;i++){
                    String accountID;
                    if(account instanceof CurrentAccountDto){
                        accountID = ((CurrentAccountDto)account).getId();
                    }
                    else{
                        accountID = ((SavingAccountDto)account).getId();
                    }
                    operationServiceImplementation.debit(accountID, (long) (Math.random()*1000), "Debit");
                    operationServiceImplementation.credit(accountID, (long)(Math.random()*2000), "Credit");
                }
            }
        };
    }
}
