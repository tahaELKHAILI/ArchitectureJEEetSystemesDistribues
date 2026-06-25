package ma.enset.tp3.web;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import ma.enset.tp3.entities.Product;
import ma.enset.tp3.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    /*
    @GetMapping("/user/index")
    public String index(Model model){
        List<Product> products = productRepository.findAll();
        model.addAttribute("productList", products);
        return "products";
    }
    */

    @GetMapping("/user/index")
    public String index(@RequestParam(defaultValue = "0") int page,
                        @RequestParam(required = false) String keyword,
                        Model model) {

        Page<Product> productPage;

        if (keyword != null && !keyword.isEmpty()) {
            productPage = productRepository
                    .findByNameContainingIgnoreCase(keyword, PageRequest.of(page, 5));
        } else {
            productPage = productRepository.findAll(PageRequest.of(page, 5));
        }

        model.addAttribute("productPage", productPage);
        model.addAttribute("keyword", keyword);

        return "products";
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @PostMapping("/admin/delete")
    public String delete(@RequestParam(name = "id") Long id){
        productRepository.deleteById(id);
        return "redirect:/user/index";
    }

    @GetMapping("/admin/newProduct")
    public String newProduct(Model model){
        model.addAttribute("product", new Product());
        return "new_product";
    }

    @PostMapping("/admin/saveProduct")
    public String saveProduct(@Valid Product product, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) return "new_product";
        productRepository.save(product);
        return "redirect:/admin/newProduct";
    }

    @GetMapping("/admin/update")
    public String showUpdateForm(@RequestParam Long id, Model model) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        model.addAttribute("product", product);

        return "editProduct";
    }

    @PostMapping("/admin/update")
    public String updateProduct(@ModelAttribute Product product) {

        productRepository.save(product);

        return "redirect:/user/index";
    }



    @GetMapping("/notAuthorized")
    public String notAuthorized(){
        return "notAuthorized";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "login";
    }
}
