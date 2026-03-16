package com.example.orderservice.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.orderservice.entity.Order;
import com.example.orderservice.repository.OrderRepository;

@RestController
@RequestMapping("/api/orderservice")
public class OrderController {

 private final OrderRepository repo;

 public OrderController(OrderRepository repo) {
  this.repo = repo;
 }

 @GetMapping
 public List<Order> getAll() {
  return repo.findAll();
 }

 @PostMapping
 public Order create(@RequestBody Order obj) {
  return repo.save(obj);
 }
}
