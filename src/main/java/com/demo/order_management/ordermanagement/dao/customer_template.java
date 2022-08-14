package com.demo.order_management.ordermanagement.dao;

import java.util.List;

import com.demo.order_management.ordermanagement.model.Customer;

public interface customer_template {
	Customer getCustomer(String email);
	boolean addCustomer(Customer customer);
	List<Customer> getCustomer();
	boolean updateCustomer(String email, String phone, String customer_address);
	boolean deleteCustomer(String email);
	
}
