package com.example.transactionpractice.shop.service;

import com.example.transactionpractice.shop.entity.Item;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
/*
EntityManager = JPA는 Entity를 기준으로 동작하는데 (영속성 컨텍스트),
                이때 Entity를 관리하는 역할을 해준다
                즉, EntityManager를 통해 영속성 컨텍스트와 소통한다
 */
public class EntityManagerService {
  private final EntityManager entityManager;

  @Transactional
  public void save(){
    Item newItem = Item.builder()
            .name("new item")
            .build();
    // save() = persist()
    entityManager.persist(newItem);
    entityManager.persist(Item.builder()
            .name("new item2")
            .build());

  }

  @Transactional
  public void find(){
    Item targetItem = entityManager.find(
            Item.class,
            1L);
    log.info(targetItem.getName());
  }


}
