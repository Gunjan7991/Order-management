package com.demo.order_management.ordermanagement.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("/customers/byemail/{email}")
	public ResponseEntity<Customer> readbyemail(@PathVariable("email") String email) {
		Customer customer= customerService.getCustomerbyEmail(email);
		if(customer == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> readall(){
		List<Customer> customer = new LinkedList<Customer>();
		customer = customerService.getCustomers();
		if(customer.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}
	
	@GetMapping("/customers/withpage")
	public ResponseEntity<List<Customer>> readallbypagination(@RequestParam("page_number") Integer page_number, @RequestParam("page_size") Integer page_size){
		List<Customer> customer = new LinkedList<Customer>();
		customer = customerService.getCustomerswithpagination(page_number, page_size);
		if(customer.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(customer, HttpStatus.OK);
	
	}
	
	@PostMapping("/customers")
	public ResponseEntity<Customer> create(@RequestBody CustomerRequest customerRequest){
		Customer customer = customerService.addCustomer(customerRequest);
		if(customer.getCustomerId() == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(customer, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/customers/{id}")
	public ResponseEntity<Boolean> deletebyId(@PathVariable("id") Long id){
		if(customerService.deleteCustomerbyID(id)) {
			return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		
	}
	
	@DeleteMapping("/customers/byemail/{email}")
	public ResponseEntity<Boolean> deletebyId(@PathVariable("email") String email){
		if(customerService.deleteCustomerbyEmail(email)) {
			return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		
	}
	
	@PutMapping("/customers/{id}")
	public ResponseEntity<Customer> updatebyid(@PathVariable("id") Long id, @RequestBody CustomerRequest customerRequest){
		Customer customer = new Customer();
		customer = customerService.update(id, customerRequest);
		if(customer == null)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(customer, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/customers/byemail/{email}")
	public ResponseEntity<Customer> updatebyEmail(@PathVariable("email") String email, @RequestBody CustomerRequest customerRequest){
		Customer customer = new Customer();
		customer = customerService.updatebyEmail(email, customerRequest);
		if(customer == null)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(customer, HttpStatus.ACCEPTED);
	}
	
}













