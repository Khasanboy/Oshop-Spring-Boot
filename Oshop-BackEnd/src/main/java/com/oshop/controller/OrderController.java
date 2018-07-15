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
import com.oshop.model.Shipping;
import com.oshop.model.User;
import com.oshop.payload.CreateOrderRequest;
import com.oshop.repository.UserRepository;
import com.oshop.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	@Autowired OrderService orderService;
	
	@Autowired UserRepository userRepository;
	
	@GetMapping("/{id}")
	public Order getOrderById(@PathVariable Long id) {
		return this.orderService.getOrderById(id).orElse(null);
	}
	
	@GetMapping("/")
	public List<Order> getAllOrders() {
		return this.orderService.getAllOrders();
	}
	
	@PostMapping("/")
	public Order createOrder(@RequestBody CreateOrderRequest orderRequest) {
		User user = this.userRepository.findById(orderRequest.getUserId()).orElse(null);
		Shipping shipping = new Shipping(orderRequest.getShipping().getName(), orderRequest.getShipping().getLine1(), orderRequest.getShipping().getLine2(), orderRequest.getShipping().getCity());
		
		Order order = new Order();
		order.setItems(orderRequest.getItems());
		order.setDatePlaced(new Date());
		order.setShipping(shipping);
		order.setUser(user);		
		return this.orderService.addOrder(order);
		
	}
	
	@DeleteMapping("/{id}")
	public void deleteOrder(@PathVariable Long id) {
		this.orderService.deleteOrder(id);
	}

}
