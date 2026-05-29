package com.ducat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ducat.entity.CartItem;
import com.ducat.entity.Product;
import com.ducat.entity.User;
import com.ducat.repository.CartItemRepository;

@Service
public class CartService {

    @Autowired
    private CartItemRepository repo;

    public CartItem addToCart(CartItem item) {

        return repo.save(item);
    }

    public List<CartItem> getCartItems(User user){

        return repo.findByUser(user);
    }
    
    public CartItem getCartItem(User user,
            Product product) {

return repo.findByUserAndProduct(user,
                     product);
}
    
    public void removeCartItem(int id) {

        repo.deleteById(id);
    }
    
    public void clearCart(User user) {

        List<CartItem> cartItems =
                repo.findByUser(user);

        repo.deleteAll(cartItems);
    }
    
    public CartItem getCartItemById(int id) {

        return repo.findById(id).orElse(null);
    }

    public void saveCartItem(CartItem item) {

        repo.save(item);
    }
}