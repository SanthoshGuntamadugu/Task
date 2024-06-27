package com.example.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Address;
import com.example.entity.Cart;
import com.example.entity.Order;
import com.example.entity.OrderDetail;
import com.example.entity.User;
import com.example.repository.Repository.AddressRepository;
import com.example.repository.Repository.CartRepository;
import com.example.repository.Repository.OrderDetailRepository;
import com.example.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Transactional
    public Order processOrder(Long userId, Long addressId) {
        List<Cart> cartItems = cartRepository.findByUser(new User(userId));
        
        BigDecimal totalAmount = cartItems.stream()
            .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        Address deliveryAddress = addressRepository.findById(addressId)
            .orElseThrow(() -> new RuntimeException("Address not found"));

        Order order = new Order();
        order.setUser(new User(userId));
        order.setTotalAmount(totalAmount);
        order.setDeliveryAddress(deliveryAddress);
        order.setOrderDate(LocalDateTime.now());

        Order savedOrder = orderRepository.save(order);

        for (Cart cartItem : cartItems) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(savedOrder);
            orderDetail.setProduct(cartItem.getProduct());
            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetail.setPrice(cartItem.getProduct().getPrice());

            orderDetailRepository.save(orderDetail);
        }

        cartRepository.deleteAll(cartItems);

        return savedOrder;
    }
}
