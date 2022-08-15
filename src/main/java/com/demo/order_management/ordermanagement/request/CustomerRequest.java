package com.demo.order_management.ordermanagement.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {


	private String customer_name;

	private String customer_address;

	private String email;

	private String phone;

	private String billing_info;

}

