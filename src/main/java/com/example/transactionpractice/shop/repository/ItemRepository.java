package com.example.transactionpractice.shop.repository;

import com.example.transactionpractice.shop.entity.Item;
import jakarta.persistence.LockModeType;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

  // 비관적 락의 Shared Lock / 읽기 가능, 쓰기 불가능
  @Lock(LockModeType.PESSIMISTIC_READ)
  // JPQL
  @Query("select i from Item i where i.id = :id ")
  Optional<Item> findItemForShare(
          @Param("id") Long id
  );

  // 비관적 락의 Exclusive Lock / 읽기 불가능, 쓰기 불가능
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  // JPQL
  @Query("select i from Item i where i.id = :id ")
  Optional<Item> findItemForUpdate(
          @Param("id") Long id
  );

  //JPA가 제공해주는 기본 기능에 락 거는 방법
//  @Override
//  @NonNull
//  @Lock(LockModeType.PESSIMISTIC_WRITE)
//  Optional<Item> findById(@NonNull Long id);
}
