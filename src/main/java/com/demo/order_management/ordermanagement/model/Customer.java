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
	private Long customer_id;

	@Column(name = "customer_name", nullable = false, length=50)
	private String customer_name;

	@Column(name = "customer_address", length=60)
	private String customer_address;

	@Column(name = "email", nullable = false, unique = true, length = 30)
	private String email;

	@Column(name = "phone", nullable = false, length=15)
	private String phone;

	@Column(name = "billing_info", nullable = false, length=50)
	private String billing_info;
	
	@OneToOne(mappedBy = "customer", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private Order order;

}
