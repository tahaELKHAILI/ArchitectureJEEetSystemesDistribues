package ma.enset.productapp;

import ma.enset.productapp.entities.Product;
import ma.enset.productapp.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(ProductRepository productRepository){
        return args -> {
            productRepository.save(Product.builder()
                    .name("Laptop HP-500")
                    .quantity(10)
                    .price(2000)
                    .selected(true)
                    .build());

            productRepository.save(Product.builder()
                    .name("Samsung NVME- 1To")
                    .quantity(20)
                    .price(100)
                    .selected(true)
                    .build());

            productRepository.save(Product.builder()
                    .name("Nitendo switch")
                    .quantity(5)
                    .price(200)
                    .selected(true)
                    .build());

            productRepository.save(Product.builder()
                    .name("Corssair Case RGB light")
                    .quantity(10)
                    .price(300)
                    .selected(false)
                    .build());

            productRepository.save(Product.builder()
                    .name("Corssair RAM 32GB RGB")
                    .quantity(10)
                    .price(150)
                    .selected(true)
                    .build());

            productRepository.save(Product.builder()
                    .name("Keyboard Rayzor")
                    .quantity(10)
                    .price(200)
                    .selected(true)
                    .build());

            productRepository.findAll().forEach(product -> {
                System.out.println(product.toString());
            });
        };
    }
}
