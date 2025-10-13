package com.fruit_ecommerce_backend.fruit_ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		
		@ManyToOne
		private Product product;
		
		
		private int quantity;
		private double price; // price at time of order
}