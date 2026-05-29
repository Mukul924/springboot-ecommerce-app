package com.ducat.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.ducat.entity.CartItem;
import com.ducat.entity.Product;
import com.ducat.entity.User;
import com.ducat.service.CartService;
import com.ducat.service.ProductService;
import com.ducat.service.UserService;


@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;
    
    @Autowired
    private UserService userService;
    
    

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id,
    		Authentication authentication) {

    	String email =
    			authentication.getName();

    			User user =
    			userService.getUserByEmail(email);
    			
        Product product =
                productService.getProductById(id);

        CartItem existingItem =
                cartService.getCartItem(user, product);

        if(existingItem != null) {

            existingItem.setQuantity(
                    existingItem.getQuantity() + 1);

            cartService.addToCart(existingItem);
        }

        else {

            CartItem item = new CartItem();

            item.setUser(user);

            item.setProduct(product);

            item.setQuantity(1);

            cartService.addToCart(item);
        }

        return "redirect:/cart";
    }
    
    
    
    @GetMapping("/cart")
    public String cartPage(Authentication authentication,
                           Model model) {

    	String email =
    			authentication.getName();

    			User user =
    			userService.getUserByEmail(email);
    			
        List<CartItem> cartItems =
                cartService.getCartItems(user);

        model.addAttribute("cartItems",
                           cartItems);

        return "cart";
    }
    
    
    
    @GetMapping("/removeCartItem/{id}")
    public String removeCartItem(@PathVariable int id) {

        cartService.removeCartItem(id);

        return "redirect:/cart";
    }
    
    
    
    @GetMapping("/buyNow/{id}")
    public String buyNow(@PathVariable int id,
    		Authentication authentication) {

    	String email =
    			authentication.getName();

    			User user =
    			userService.getUserByEmail(email);
    			
        Product product = productService.getProductById(id);

        CartItem existingItem =
                cartService.getCartItem(user, product);

        if(existingItem != null) {

            existingItem.setQuantity(
                    existingItem.getQuantity() + 1);

            cartService.addToCart(existingItem);
        }

        else {

            CartItem item = new CartItem();

            item.setUser(user);

            item.setProduct(product);

            item.setQuantity(1);

            cartService.addToCart(item);
        }

        return "redirect:/checkout";
    }

    
    
    @GetMapping("/increaseQty/{id}")
    public String increaseQty(@PathVariable int id) {

        CartItem item =
                cartService.getCartItemById(id);

        item.setQuantity(
                item.getQuantity() + 1);

        cartService.saveCartItem(item);

        return "redirect:/cart";
    }
    
    
    
    @GetMapping("/decreaseQty/{id}")
    public String decreaseQty(@PathVariable int id) {

        CartItem item =
                cartService.getCartItemById(id);

        if(item.getQuantity() > 1) {

            item.setQuantity(
                    item.getQuantity() - 1);

            cartService.saveCartItem(item);
        }

        return "redirect:/cart";
    }
    
}