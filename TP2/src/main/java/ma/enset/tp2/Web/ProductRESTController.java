package ma.enset.tp2.Web;

import ma.enset.tp2.Entity.Product;
import ma.enset.tp2.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.DeleteExchange;

import java.util.List;

@RestController
public class ProductRESTController {
    @Autowired //Better use AllAeg contructor
    private ProductRepository productRepository;

    //Get all products
    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    //Get product by ID
    @GetMapping("/products/{id}")
    public Product getProductByID(@PathVariable Long id){
        return productRepository.findById(id).orElseThrow(()-> new RuntimeException("ID not found"));
    }

    //Get products with name containingg keyword
    @GetMapping("/find/products")
    public List<Product> getProductByKyWord(@RequestParam(name = "kw", defaultValue = "") String kw){
        return productRepository.findByNameContaining(kw);
    }

    //Add product to bd
    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product){
        return productRepository.save(product);
    }

    //Delete product by ID
    @DeleteExchange("/products/{id}")
    public void deleteProductByID(@PathVariable Long id){
        productRepository.deleteById(id);
    }
}
