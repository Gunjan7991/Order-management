package com.demo.order_management.ordermanagement.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
	String product_name;
	Float price;
	Integer quantity;

}
