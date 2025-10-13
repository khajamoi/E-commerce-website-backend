package com.fruit_ecommerce_backend.fruit_ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fruit_ecommerce_backend.fruit_ecommerce.entity.Product;
import com.fruit_ecommerce_backend.fruit_ecommerce.service.ProductService;

import java.util.List;


@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
		private final ProductService productService;
		
		
		@GetMapping
		public ResponseEntity<List<Product>> list(){
		return ResponseEntity.ok(productService.listAll());
		}
		
		
		@GetMapping("/{id}")
		public ResponseEntity<Product> get(@PathVariable Long id){
		return productService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
		}
		
		
}