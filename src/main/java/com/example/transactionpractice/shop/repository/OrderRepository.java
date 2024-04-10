package com.example.transactionpractice.shop.repository;

import com.example.transactionpractice.shop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
