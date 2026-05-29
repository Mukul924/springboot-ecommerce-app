package com.ducat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ducat.entity.CartItem;
import com.ducat.entity.Product;
import com.ducat.entity.User;

public interface CartItemRepository
extends JpaRepository<CartItem, Integer>{

    List<CartItem> findByUser(User user);

    CartItem findByUserAndProduct(User user,
                                  Product product);

}