package ma.enset.productapp.controllers;

import ma.enset.productapp.entities.Product;
import ma.enset.productapp.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProdutController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public Product findById(@PathVariable(name = "id") Long id){
        return productRepository.findById(id).get();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable(name = "id") Long id){
        productRepository.deleteById(id);
    }

}
