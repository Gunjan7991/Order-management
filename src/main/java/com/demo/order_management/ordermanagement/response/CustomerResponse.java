package com.demo.order_management.ordermanagement.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
		private Long customerId;
		private String customerName;
		private String customerAddress;
		private String email;
		private String password;

}
