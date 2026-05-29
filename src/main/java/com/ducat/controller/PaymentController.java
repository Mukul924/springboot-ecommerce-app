package com.ducat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.ducat.entity.CartItem;
import com.ducat.entity.User;
import com.ducat.service.CartService;
import com.ducat.service.UserService;

@Controller
public class PaymentController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;
    

    @PostMapping("/processPayment")
    public String processPayment(String address, String paymentMethod, Authentication authentication, Model model) {

        String email = authentication.getName();

        User user = userService.getUserByEmail(email);

        List<CartItem> cartItems = cartService.getCartItems(user);

        double totalAmount = 0;

        for(CartItem item : cartItems){

            totalAmount += item.getProduct().getPrice()*item.getQuantity();
            
        }

        model.addAttribute("cartItems", cartItems);

        model.addAttribute("totalAmount", totalAmount);

        model.addAttribute("address", address);

        model.addAttribute("paymentMethod", paymentMethod);

        return "payment";
    }
    
    
}