package com.ducat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ducat.entity.Product;
import com.ducat.entity.User;
import com.ducat.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public Product addProduct(Product product) {

        return repo.save(product);
    }

    public List<Product> getAllProducts() {

        return repo.findAll();
    }
    
    public Product getProductById(int id) {

        return repo.findById(id).orElse(null);
    }
    
    public List<Product> searchProducts(String keyword){

        return repo.findByNameContainingIgnoreCase(keyword);
    }
    
    public List<Product> getProductsByCategory(String category){

        return repo.findByCategoryIgnoreCase(category);
    }
    
    public List<Product> getSellerProducts(User seller){

        return repo.findBySeller(seller);
    }
    
    public void deleteProduct(int id){

        repo.deleteById(id);
    }
    
    public int getSellerProductCount(User seller){

        return repo.findBySeller(seller).size();
    }
}