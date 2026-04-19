package ma.enset.tp2.Web;

import ma.enset.tp2.Entity.Product;
import ma.enset.tp2.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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


}
