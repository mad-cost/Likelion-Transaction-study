package com.example.transactionpractice.shop.repository;

import com.example.transactionpractice.shop.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
