package com.oshop.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oshop.model.Order;
import com.oshop.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	@Autowired OrderService orderService;
	
	@GetMapping("/{id}")
	public Order getOrderById(@PathVariable Long id) {
		return this.orderService.getOrderById(id).orElse(null);
	}
	
	@GetMapping("/")
	public List<Order> getAllOrders() {
		return this.orderService.getAllOrders();
	}
	
	@PostMapping("/")
	public void createOrder(@RequestBody Order order) {
		Order ord = new Order();
		ord.setItems(order.getItems());
		ord.setDatePlaced(new Date());
		
		this.orderService.addOrder(order);
	}
	
	@DeleteMapping("/{id}")
	public void deleteOrder(@PathVariable Long id) {
		this.orderService.deleteOrder(id);
	}

}
