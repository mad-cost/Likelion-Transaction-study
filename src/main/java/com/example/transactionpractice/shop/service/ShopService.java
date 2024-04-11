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

  @Transactional
  /*
  First Level Cache = 한 번의 트랜잭션에서 조회된 적 있는 데이터는 컨텍스트에서 관리
                      방금 생성한 데이터나 여러번 조회하는 데이터의 성능 향상
   */
  public void testIdentity(){
    Item item = Item.builder().build();
    Long id = itemRepository.save(item).getId();

    Item a = itemRepository.findById(id).get();
    Item b = itemRepository.findById(id).get();
    // a,b가 참조하고 있는 영속성 컨텍스트의 객체가 같기 때문에 true 반환
    log.info("is same object : {}", a==b);
  }

  @Transactional
  public void testDirtyChecking(){
    /*
    dirty Checking = 트랜젝션 내부에서 Entity 수정시 변경 사항에 대한 update문 자동 생성
                     즉, 내가 실행하지 않은 Hibernate: update 자동 생성
     */
    itemRepository.findAll().stream()
            .forEach(item -> item.setStock(100));
  }

  // 낙관적 락 TestCode 작성
  @Transactional
  public void decreaseStockOpt(){
    Item item = itemRepository.findById(1L)
            .orElseThrow();
    item.setStock(item.getStock() - 10);
    itemRepository.save(item);
  }

  @Transactional
  // 비관적 락 / Shared Lock Test
  public void decreaseStockShare(){
    Item item = itemRepository.findItemForShare(1L)
            .orElseThrow();
    item.setStock(item.getStock() - 10);
    itemRepository.save(item);
  }

  @Transactional
  // 비관적 락 / Exclusive Lock Test
  public void decreaseStockUpdate(){
    Item item = itemRepository.findItemForUpdate(1L)
            .orElseThrow();
    item.setStock(item.getStock() - 10);
    itemRepository.save(item);
  }

  @Transactional
  public void decreaseStockOver(){
    // ItemRepository의 findById가 Exclusive Lock이 걸려있다.
    Item item = itemRepository.findById(1L).orElseThrow();
    item.setStock(item.getStock() - 10);
    itemRepository.save(item);
  }
}
