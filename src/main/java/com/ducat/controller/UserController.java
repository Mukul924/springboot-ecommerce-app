package com.ducat.controller;

import java.util.List;


import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ducat.entity.Product;
import com.ducat.entity.User;
import com.ducat.service.OrderService;
import com.ducat.service.ProductService;
import com.ducat.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService service;
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private OrderService orderService;
    
    

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/signup")
    public String signupPage(Model model) {

        model.addAttribute("user", new User());

        return "signup";
    }

    @PostMapping("/register")
    public String registerUser(User user) {

        user.setPassword(

            passwordEncoder.encode(
                    user.getPassword()
            )
        );

        service.registerUser(user);

        return "login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

        
    
    @GetMapping("/sellerDashboard")
    public String sellerDashboard(Authentication authentication,
                                  Model model){

        String email =
                authentication.getName();

        User seller =
                service.getUserByEmail(email);

        model.addAttribute("user",
                           seller);

        int totalOrders =
                orderService
                .getSellerOrderCount(seller);

        model.addAttribute(
                "totalOrders",
                totalOrders);

        int pendingOrders =
                orderService
                .getPendingOrders(seller);

        model.addAttribute(
                "pendingOrders",
                pendingOrders);

        double totalRevenue =
                orderService
                .getTotalRevenue(seller);

        model.addAttribute(
                "totalRevenue",
                totalRevenue);

        List<Product> products =
                productService
                .getSellerProducts(seller);

        model.addAttribute("products",
                           products);

        int totalProducts =
                productService
                .getSellerProductCount(seller);

        model.addAttribute("totalProducts",
                           totalProducts);

        return "seller-dashboard";
    }
}