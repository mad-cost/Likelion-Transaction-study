package com.example.transactionpractice.shop.repository;

import com.example.transactionpractice.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
