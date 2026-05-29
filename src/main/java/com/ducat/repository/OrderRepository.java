package com.ducat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ducat.entity.OrderEntity;
import com.ducat.entity.User;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer>{

    List<OrderEntity> findByUser(User user);
    List<OrderEntity> findBySeller(User seller);
    int countBySeller(User seller);

    List<OrderEntity>
    findBySellerAndOrderStatus(
            User seller,
            String orderStatus);

}