package com.example.transactionpractice.shop.repository;

import com.example.transactionpractice.shop.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
