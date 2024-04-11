package com.example.transactionpractice.shop.controller;

import com.example.transactionpractice.shop.service.EntityManagerService;
import com.example.transactionpractice.shop.service.ParentService;
import com.example.transactionpractice.shop.service.ShopService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController //@Controller + @ResponseBody = Json 형태로 객체 데이터를 반환
@RequiredArgsConstructor
@RequestMapping("/shop")
public class ShopController {
  private final ShopService shopService;
  private final ParentService parentService;
  private final EntityManagerService emService;

  @Transactional //springframework
  @GetMapping("/create-order")
  public String createOrder(){
  shopService.createOrder();
    return "done";
  }


  @GetMapping("/propagationOne")
  // Hibernate : insert흐름 따라가보기
  public void propagaionOne(){
    log.info("noneOne");
    parentService.noneOne();
  }
  @GetMapping("/propagationTwo")
  public void propagaionTwo(){
    log.info("noneTwo");
    parentService.noneTwo();
  }
  @GetMapping("/propagationThree")
  public void propagaionThree(){
    log.info("noneThree");
    parentService.noneThree();
  }

  // 영속성 컨텍스트(Persistence Context)테스트
  @GetMapping("/identity")
  public void identity(){
    shopService.testIdentity();
  }
  // 영속성 컨텍스트(Persistence Context)테스트
  @GetMapping("/dirty-check")
  public void dirtyCheck(){
    shopService.testDirtyChecking();
  }

  // 영속성 컨텍스트와 소통하는 EntityManager 사용해보기
  @GetMapping("/test-em")
  public void testEm(){
    emService.save();
    emService.find();
  }






}
