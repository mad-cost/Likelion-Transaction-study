package com.example.transactionpractice.shop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String description;
  private Integer price;
  @Setter
  private Integer stock; // 재고
  
  // 동시성 문제 제어
  @Version
  private Long version;

  @ManyToOne
  private Shop shop;
  @ManyToOne
  @JoinColumn(name="category_id")
  private ItemCategory itemCategory;

  @ManyToMany(mappedBy = "items")
  private final List<Order> orders = new ArrayList<>();
}
