package ma.enset.tp3;

import ma.enset.tp3.entities.Product;
import ma.enset.tp3.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Tp3Application {

    public static void main(String[] args) {
        SpringApplication.run(Tp3Application.class, args);
    }

    @Bean
    public CommandLineRunner start(ProductRepository productRepository){
        return args -> {
            productRepository.save(Product.builder()
                            .name("PC Portable")
                            .price(2000)
                            .quantity(5)
                    .build());
            productRepository.save(Product.builder()
                    .name("NVME 1To")
                    .price(500)
                    .quantity(10)
                    .build());
            productRepository.save(Product.builder()
                    .name("RAM 32GB")
                    .price(200)
                    .quantity(10)
                    .build());

            productRepository.findAll().forEach(product -> {
                System.out.println(product.toString());
            });
        };
    }
}
