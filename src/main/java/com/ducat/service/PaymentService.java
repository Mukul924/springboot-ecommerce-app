package com.ducat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ducat.entity.OrderEntity;
import com.ducat.entity.Payment;
import com.ducat.repository.PaymentRepository;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repo;

    public Payment savePayment(
            Payment payment){

        return repo.save(payment);
    }
    
    public Payment createPayment(

            OrderEntity order,

            String paymentMethod,

            double total){

        Payment payment =
                new Payment();

        payment.setAmount(total);

        payment.setPaymentMethod(
                paymentMethod);

        if(paymentMethod.equals("COD")){

            payment.setPaymentStatus(
                    "PENDING");
        }

        else{

            payment.setPaymentStatus(
                    "PAID");
        }

        if(!paymentMethod.equals("COD")){

            payment.setTransactionId(

                    "TXN" +
                    System.currentTimeMillis());
        }

        payment.setOrder(order);

        order.setPayment(payment);

        return repo.save(payment);
    }
}