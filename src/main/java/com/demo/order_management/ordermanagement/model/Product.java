package com.demo.order_management.ordermanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	
	@Id
	@Column(name="product_id", nullable=false, unique=true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long  productId; //changed here
	
	@Column(name="product_name", nullable=false)
	private String product_name;
	
	@Column(name="product_price", nullable=false)
	private Double product_price;
	
	@Column(name="product_quantity", nullable=false)
	private Integer product_quantity;
	
	
}
