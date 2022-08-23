package com.demo.order_management.ordermanagement.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

	@Id
	@Column(name = "customer_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;

	@Column(name = "customer_name", nullable = false, length=50)
	private String customerName;

	@Column(name = "customer_address", length=60)
	private String customerAddress;

	@Column(name = "email", nullable = false, unique = true, length = 30)
	private String email;
	
	@Column(name = "password", nullable = false, unique = true, length = 30)
	private String password;

	@Column(name = "phone", nullable = false, length=15)
	private String phone;

	@Column(name = "billing_info", length=50)
	private String billingInfo;
	
	@OneToOne(mappedBy = "customer", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private Invoice invoice;

}
