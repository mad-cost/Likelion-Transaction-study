package com.example.transactionpractice.shop.service;

import com.example.transactionpractice.shop.entity.Customer;
import com.example.transactionpractice.shop.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChildService {
  private final CustomerRepository customerRepository;

  /*
  SUPPORTS = 나를 호출한 메서드가 트랜잭션이면 그 일부로 실행되고,
             아니라면 트랜잭션 없이 실행된다
   */
  @Transactional(propagation = Propagation.SUPPORTS)
  public void supports(){
    customerRepository.save(Customer.builder()
            .name("Child Supports")
            .build());
    throw new RuntimeException("child throw");
  }

  /*
  MANDATORY = 나를 호출한 메서드가 트랜잭션 이어야 한다.
              없으면 예외가 발생한다
   */
  @Transactional(propagation = Propagation.MANDATORY)
  public void mandatory(){
    customerRepository.save(Customer.builder()
            .name("Child Mandatory")
            .build());
    throw new RuntimeException("child throw");
  }
}
