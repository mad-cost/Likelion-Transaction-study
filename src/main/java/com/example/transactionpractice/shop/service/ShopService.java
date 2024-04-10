package com.example.transactionpractice.shop.service;

import com.example.transactionpractice.shop.entity.Customer;
import com.example.transactionpractice.shop.entity.Item;
import com.example.transactionpractice.shop.entity.Order;
import com.example.transactionpractice.shop.entity.OrderItem;
import com.example.transactionpractice.shop.repository.CustomerRepository;
import com.example.transactionpractice.shop.repository.ItemRepository;
import com.example.transactionpractice.shop.repository.OrderItemRepository;
import com.example.transactionpractice.shop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShopService {
  private final CustomerRepository customerRepository;
  private final ItemRepository itemRepository;
  private final OrderRepository orderRepository;
  private final OrderItemRepository orderItemRepository;

  /*
  REQUIRED = 나를 호출한 메서드가 트랜잭션이면 그 일부로 실행되고,
             아니라면 내가 직접 트랜잭션을 만든다
   */
  @Transactional(propagation = Propagation.REQUIRED)
  public void createOrder(){
    // 고객 정보 회수
    Customer customer = customerRepository
            .findById(1L).orElseThrow();
    // 고객의 새 주문 생성
    Order newOrder = orderRepository.save(Order.builder()
            .customer(customer)
            .build());
    // 구매할 물품 회수
    Item item = itemRepository
            .findById(2L).orElseThrow();
    // 주문정보의 물품 추가
    orderItemRepository.save(OrderItem.builder()
            .order(newOrder)
            .item(item)
            .count(10)
            .build());

    if (!(item.getStock() < 10)){
      item.setStock(item.getStock() - 10);
      itemRepository.save(item);
    }else throw new IllegalStateException();
  }












}
