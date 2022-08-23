package com.demo.order_management.ordermanagement.response;

import com.demo.order_management.ordermanagement.model.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseOrderResponse {
	private Order data;
	private String message;

}
