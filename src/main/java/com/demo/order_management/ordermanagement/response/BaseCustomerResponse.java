package com.demo.order_management.ordermanagement.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseCustomerResponse {
	private CustomerResponse data;
	private String message;

}
