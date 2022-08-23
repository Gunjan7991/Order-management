package com.demo.order_management.ordermanagement.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {


	private String customerName;

	private String customerAddress;

	private String email;
	
	private String password;

	private String phone;

	private String billingInfo;

}

