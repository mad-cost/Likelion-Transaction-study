package com.example.transactionpractice.shop.service;

import com.example.transactionpractice.shop.entity.Customer;
import com.example.transactionpractice.shop.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParentService {
  private final CustomerRepository customerRepository;
  private final ChildService childService;

  @Transactional
  public void noneOne(){
    customerRepository.save(Customer.builder()
            .name("Parent None1")
            .build())  ;
    try {
      childService.supports();
    } catch (Exception e){
      log.warn(e.getMessage());
    }
    customerRepository.save(Customer.builder()
            .name("Parent None 2")
            .build());
    throw new RuntimeException("parent throw");
  }

  @Transactional
  public void noneTwo(){
    //@Transactional이 있을때와 없을때 차이점 인지하기
    customerRepository.save(Customer.builder()
            .name("Parent None1")
            .build())  ;
    try {
      childService.mandatory();
    } catch (Exception e){
      log.warn(e.getMessage());
    }
    customerRepository.save(Customer.builder()
            .name("Parent None 2")
            .build());
    throw new RuntimeException("parent throw");
  }

  //@Transactional
  public void noneThree(){
    customerRepository.save(Customer.builder()
            .name("Parent None1")
            .build())  ;
    try {
      childService.never();
    } catch (Exception e){
      log.warn(e.getMessage());
    }
    customerRepository.save(Customer.builder()
            .name("Parent None 2")
            .build());
    throw new RuntimeException("parent throw");
  }
}
