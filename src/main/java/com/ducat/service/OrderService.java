package com.ducat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ducat.entity.OrderEntity;
import com.ducat.entity.User;
import com.ducat.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repo;

    public OrderEntity saveOrder(OrderEntity order) {

        if(order.getOrderStatus() == null){

            order.setOrderStatus("PENDING");
        }

        return repo.save(order);
    }
    
    public List<OrderEntity> getOrders(User user){

        return repo.findByUser(user);
    }
    
    public List<OrderEntity> getSellerOrders(User seller){

        return repo.findBySeller(seller);
    }
    
    public int getSellerOrderCount(User seller){

        return repo.countBySeller(seller);
    }
    
    public int getPendingOrders(User seller){

        return repo
        .findBySellerAndOrderStatus(
                seller,
                "PENDING")
        .size();
    }
    
    public double getTotalRevenue(User seller){

        List<OrderEntity> orders =
                repo.findBySeller(seller);

        double total = 0;

        for(OrderEntity order : orders){

            total += order.getTotalAmount();
        }

        return total;
    }
    
    public OrderEntity getOrderById(int id){

        return repo.findById(id).orElse(null);
    }
}

