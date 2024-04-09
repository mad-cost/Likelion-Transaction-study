package com.example.transactionpractice.shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer_order")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String totalPrice;
  @ManyToOne
  private Customer customer;
  @ManyToOne
  private PaymentMethod payment;
  private String address;

  @ManyToMany
  @JoinTable(
          name = "order_item",
          joinColumns = @JoinColumn(name = "order_id"),
          inverseJoinColumns = @JoinColumn(name = "item_id")
  )
  private final List<Item> items = new ArrayList<>();

}
