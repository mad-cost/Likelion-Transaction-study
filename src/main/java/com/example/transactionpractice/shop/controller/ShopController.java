package com.example.transactionpractice.shop.controller;

import com.example.transactionpractice.shop.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //@Controller + @ResponseBody = Json 형태로 객체 데이터를 반환
@RequiredArgsConstructor
@RequestMapping("/shop")
public class ShopController {
  private final ShopService shopService;

  @GetMapping("/create-order")
  public String createOrder(){
  shopService.createOrder();


    return "done";
  }




}
