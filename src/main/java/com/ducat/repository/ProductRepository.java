package com.ducat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ducat.entity.Product;
import com.ducat.entity.User;

public interface ProductRepository extends JpaRepository<Product, Integer>{

    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByCategoryIgnoreCase(String category);
    List<Product> findBySeller(User seller);

}