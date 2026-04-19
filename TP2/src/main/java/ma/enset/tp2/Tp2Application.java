package ma.enset.tp2;

import ma.enset.tp2.Entity.Product;
import ma.enset.tp2.Repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Tp2Application {

    public static void main(String[] args) {

        SpringApplication.run(Tp2Application.class, args);

    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository) {
        return args -> {
            Product p1 = Product.builder()
                    .name("Laptop").price(20000).quantity(5)
                    .build();
            Product p2 = Product.builder()
                    .name("Mouse").price(10).quantity(20)
                    .build();
            Product p3 = Product.builder()
                    .name("USB").price(20).quantity(50)
                    .build();

            productRepository.save(p1);
            productRepository.save(p2);
            productRepository.save(p3);

            List<Product> products = productRepository.findAll();

            products.forEach(product -> {
                System.out.println(product.toString());
            });
        };
    }
}
