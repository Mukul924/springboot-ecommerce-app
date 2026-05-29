package com.ducat.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.ducat.entity.Product;
import com.ducat.entity.User;
import com.ducat.service.ProductService;
import com.ducat.service.UserService;

@Controller
public class ProductController {

    @Autowired
    private ProductService service;
    
    @Autowired
    private UserService userService;

    @GetMapping("/addProduct")
    public String addProductPage(Model model) {

        model.addAttribute("product",
                           new Product());

        return "add-product";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(Product product,Authentication authentication, @RequestParam("imageFile") MultipartFile file) throws IOException {

    	String email =
    			authentication.getName();

    			User seller =
    			userService.getUserByEmail(email);

        product.setSeller(seller);
        
        String fileName =
                file.getOriginalFilename();

        String uploadDir =
                "src/main/resources/static/images/";

        Path path =
        Paths.get(uploadDir + fileName);

        Files.write(path, file.getBytes());

        product.setImageUrl(fileName);

        service.addProduct(product);

        return "redirect:/sellerDashboard";
    }

    @GetMapping("/products")
    public String productsPage(Model model,
    		Authentication authentication) {

        List<Product> products =
                service.getAllProducts();

        String email =
    			authentication.getName();

    			User user =
    			userService.getUserByEmail(email);

        model.addAttribute("products",
                           products);

        model.addAttribute("user",
                           user);

        return "user-dashboard";
    }    
    @GetMapping("/product/{id}")
    public String productDetails(@PathVariable int id,
                                 Model model) {

        Product product =
                service.getProductById(id);

        model.addAttribute("product",
                           product);

        return "product-details";
    }
    
    @GetMapping("/search")
    public String searchProducts(String keyword,
                                 Model model,
                                 Authentication authentication ) {

        List<Product> products =
                service.searchProducts(keyword);

        String email =
    			authentication.getName();

    			User user =
    			userService.getUserByEmail(email);

        model.addAttribute("products",
                           products);

        model.addAttribute("user",
                           user);

        if(user.getRole().equals("SELLER")) {

            return "seller-dashboard";

        } else {

            return "user-dashboard";
        }
    }
    
    @GetMapping("/category/{category}")
    public String categoryProducts(@PathVariable String category,
                                   Model model,
                                   Authentication authentication ) {

        List<Product> products =
                service.getProductsByCategory(category);

        String email =
    			authentication.getName();

    			User user =
    			userService.getUserByEmail(email);

        model.addAttribute("products",
                           products);

        model.addAttribute("user",
                           user);

        return "user-dashboard";
    }
    
    @GetMapping("/manageProducts")
    public String manageProducts(Authentication authentication,
                                 Model model) {

    	String email =
    			authentication.getName();

    			User seller =
    			userService.getUserByEmail(email);

        List<Product> products =
                service.getSellerProducts(seller);

        model.addAttribute("products",
                           products);

        return "manage-products";
    }
    
    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable int id){

        service.deleteProduct(id);

        return "redirect:/sellerDashboard";
    }
    
    @GetMapping("/editProduct/{id}")
    public String editProduct(@PathVariable int id,
                              Model model) {

        Product product =
                service.getProductById(id);

        model.addAttribute("product",
                           product);

        return "edit-product";
    }
    
    @PostMapping("/updateProduct")
    public String updateProduct(Product product, @RequestParam("imageFile") MultipartFile file,
    		Authentication authentication) throws IOException {

    	String email =
    			authentication.getName();

    			User seller =
    			userService.getUserByEmail(email);

        product.setSeller(seller);
        
        String fileName =
                file.getOriginalFilename();

        String uploadDir =
                "src/main/resources/static/images/";

        Path path =
        Paths.get(uploadDir + fileName);

        Files.write(path, file.getBytes());

        product.setImageUrl(fileName);

        service.addProduct(product);

        return "redirect:/sellerDashboard";
    }
}