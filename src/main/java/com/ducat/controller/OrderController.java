package com.ducat.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.ducat.entity.CartItem;
import com.ducat.entity.OrderEntity;
import com.ducat.entity.User;
import com.ducat.service.CartService;
import com.ducat.service.OrderService;
import com.ducat.service.PaymentService;
import com.ducat.service.UserService;


@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private PaymentService paymentService;
    
    

    @GetMapping("/checkout")
    public String checkoutPage(
            Model model,
            Authentication authentication) {

        String email =
                authentication.getName();

        User user =
                userService.getUserByEmail(email);

        List<CartItem> cartItems =
                cartService.getCartItems(user);

        model.addAttribute(
                "cartItems",
                cartItems);

        return "checkout";
    }
    

    
    @PostMapping("/placeOrder")
    public String placeOrder(String address, String paymentMethod, Authentication authentication, Model model) {

    	String email =
    			authentication.getName();

    			User user =
    			userService.getUserByEmail(email);

        List<CartItem> cartItems =
                cartService.getCartItems(user);
        
        OrderEntity lastOrder = null;
        double total = 0;

        for(CartItem item : cartItems) {

            OrderEntity order =
                    new OrderEntity();

            total = item.getProduct().getPrice()
                  * item.getQuantity();

            order.setUser(user);

            order.setProduct(item.getProduct());

            order.setQuantity(item.getQuantity());

            order.setAddress(address);

            order.setOrderStatus("PENDING");

            order.setTotalAmount(total);

            order.setSeller(
                    item.getProduct().getSeller());

        
            lastOrder = orderService.saveOrder(order);
            
            paymentService.createPayment(
                    lastOrder,
                    paymentMethod,
                    total);
            }

        cartService.clearCart(user);

        model.addAttribute(
                "order",
                lastOrder);
        
        model.addAttribute(
                "paymentMethod",
                paymentMethod);
        
        if(lastOrder == null){

            return "redirect:/cart";
        }

        model.addAttribute(
                "paymentStatus",
                lastOrder
                .getPayment()
                .getPaymentStatus());

        return "order-success";
    }
    
    
    
    @GetMapping("/myOrders")
    public String myOrders(Authentication authentication,
                           Model model) {

    	String email =
    			authentication.getName();

    			User user =
    			userService.getUserByEmail(email);

        List<OrderEntity> orders =
                orderService.getOrders(user);

        model.addAttribute("orders",
                           orders);

        return "orders";
    }
    
    
        
    @GetMapping("/sellerOrders")
    public String sellerOrders(Authentication authentication,
                               Model model){

    	String email =
    			authentication.getName();

    			User user =
    			userService.getUserByEmail(email);
        List<OrderEntity> orders =
        orderService.getSellerOrders(user);

        model.addAttribute("orders",
                           orders);

        return "seller-orders";
    }
    
    
    
    @GetMapping("/updateOrderStatus/{id}/{status}")
    public String updateOrderStatus(
            @PathVariable int id,
            @PathVariable String status){

        OrderEntity order =
                orderService.getOrderById(id);

        order.setOrderStatus(status);

        orderService.saveOrder(order);

        return "redirect:/sellerOrders";
    }
    
}