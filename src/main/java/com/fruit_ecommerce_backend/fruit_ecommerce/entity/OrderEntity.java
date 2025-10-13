package com.fruit_ecommerce_backend.fruit_ecommerce.entity;

import java.time.Instant;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
		
		
		@ManyToOne 
		private User user;
		
		
		@OneToMany(cascade = CascadeType.ALL)
		private List<OrderItem> items;
		
		
		private double total;
		
		
		private String status; // PENDING, SHIPPED, DELIVERED
		
		
		private Instant createdAt = Instant.now();
}