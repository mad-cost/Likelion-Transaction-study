package com.example.transactionpractice.shop.controller;

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

  @Transactional //springframework
  @GetMapping("/create-order")
  public String createOrder(){
  shopService.createOrder();
    return "done";
  }


  @GetMapping("propagationOne")
  // Hibernate : insert흐름 따라가보기
  public void propagaionOne(){
    log.info("noneOne");
    parentService.noneOne();
  }
  @GetMapping("propagationTwo")
  public void propagaionTwo(){
    log.info("noneTwo");
    parentService.noneTwo();
  }




}
