package com.demo.order_management.ordermanagement.request;


import com.demo.order_management.ordermanagement.model.Customer;
import com.demo.order_management.ordermanagement.model.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
	
	private Customer customer;
	private Product product;
	private Integer quantity;
	private Double total; 
}
