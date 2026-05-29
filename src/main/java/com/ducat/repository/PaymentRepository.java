package com.ducat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ducat.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

}