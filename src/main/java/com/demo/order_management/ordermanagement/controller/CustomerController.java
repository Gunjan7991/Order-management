package com.demo.order_management.ordermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.order_management.ordermanagement.model.Customer;
import com.demo.order_management.ordermanagement.request.CustomerRequest;
import com.demo.order_management.ordermanagement.service.CustomerService;

@RestController
@RequestMapping(value="/amazon/v1/api")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@GetMapping("/customers/{id}")
	public ResponseEntity<Customer> read(@PathVariable("id") Long id) {
		Customer customer= customerService.getCustomer(id);
		if(customer == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}
	
	@PostMapping("/customers")
	public ResponseEntity<Customer> create(@RequestBody CustomerRequest customerRequest){
		Customer customer = customerService.addCustomer(customerRequest);
		if(customer.getCustomer_id() == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(customer, HttpStatus.CREATED);
	}
}
