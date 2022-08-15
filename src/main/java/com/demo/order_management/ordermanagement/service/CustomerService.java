package com.demo.order_management.ordermanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.order_management.ordermanagement.dao.CustomerDao;
import com.demo.order_management.ordermanagement.model.Customer;
import com.demo.order_management.ordermanagement.request.CustomerRequest;

@Service
public class CustomerService {
	
	@Autowired
	CustomerDao customerDao;
	
	public Customer getCustomer(Long id){
		Customer customer = new Customer();
		try {
			customer = customerDao.findByCustomerId(id);
			return customer;
		}catch(Exception e) {
			//System.out.println("Exception: "+ e);
		}
		return customer;
	}
	
	public Customer addCustomer(CustomerRequest customerRequest) {
		Customer customer = new Customer();
		try {
			customer.setCustomer_name(customerRequest.getCustomer_name());
			customer.setEmail(customerRequest.getEmail());
			customer.setPhone(customerRequest.getPhone());
			customer.setBilling_info(customerRequest.getBilling_info());
			customer.setCustomer_address(customerRequest.getCustomer_address());
			customer = customerDao.save(customer);
			return customer;
		}
		catch(Exception e) {
			//System.out.println("Exception: "+ e);
		}
		return customer;
	}

}
