package com.fruit_ecommerce_backend.fruit_ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.fruit_ecommerce_backend.fruit_ecommerce.dto.OrderDTO;
import com.fruit_ecommerce_backend.fruit_ecommerce.entity.OrderEntity;
import com.fruit_ecommerce_backend.fruit_ecommerce.entity.OrderItem;
import com.fruit_ecommerce_backend.fruit_ecommerce.entity.Product;
import com.fruit_ecommerce_backend.fruit_ecommerce.entity.User;
import com.fruit_ecommerce_backend.fruit_ecommerce.repository.ProductRepository;
import com.fruit_ecommerce_backend.fruit_ecommerce.repository.UserRepository;
import com.fruit_ecommerce_backend.fruit_ecommerce.service.OrderService;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
		private final OrderService orderService;
		private final ProductRepository productRepository;
		private final UserRepository userRepository;
		
		
		@PostMapping
		public ResponseEntity<?> placeOrder(@RequestBody OrderDTO dto, Authentication authentication){
		String email = authentication.getName();
		User user = userRepository.findByEmail(email).orElseThrow();
		
		
		List<OrderItem> items = new ArrayList<>();
		for(OrderDTO.OrderItemDTO it : dto.getItems()){
		Product p = productRepository.findById(it.getProductId()).orElseThrow();
		OrderItem oi = new OrderItem();
		oi.setProduct(p);
		oi.setQuantity(it.getQty());
		oi.setPrice(p.getPrice());
		items.add(oi);
		// reduce stock (simple)
		p.setStock(p.getStock() - it.getQty());
		productRepository.save(p);
		}
		
		
		OrderEntity order = new OrderEntity();
		order.setUser(user);
		order.setItems(items);
		order.setTotal(dto.getTotal());
		order.setStatus("PENDING");
		
		
		OrderEntity saved = orderService.placeOrder(order);
		return ResponseEntity.ok(saved);
		}
}