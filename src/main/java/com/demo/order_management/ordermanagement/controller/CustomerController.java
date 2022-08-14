package com.demo.order_management.ordermanagement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.order_management.ordermanagement.dao.CustomerDao;
import com.demo.order_management.ordermanagement.model.Customer;

@RestController
public class CustomerController {

	@Autowired
	private CustomerDao customerDao;

	@GetMapping("/customer")
	public Map<String, Customer> read(@RequestParam String email, Model model) {
		Customer customer = new Customer();
		customer = customerDao.getCustomer(email);
		HashMap<String, Customer> map = new HashMap<>();
		map.put("Data", customer);
		return map;
	}

//
	@PostMapping("/customers")
	public Map<String, String> create(@RequestParam String name, @RequestParam String address, @RequestParam String email, @RequestParam String phone, @RequestParam String billing, Model model) {
		
	//public Map<String, String> create(@RequestBody String name, @RequestBody String address, @RequestBody String email, @RequestBody String phone, @RequestBody String billing, Model model) {
		
		HashMap<String, String> map = new HashMap<>();
		Customer customer = new Customer();
		customer.setCustomer_name(name);
		customer.setCustomer_address(address);
		customer.setEmail(email);
		customer.setPhone(phone);
		customer.setBilling_info(billing);

		boolean added = customerDao.addCustomer(customer);
		if (added) {
			map.put("Data", "Customer Added Sucessfully.");
			return map;
		} else {
			map.put("Data", "Something went Wrong!!!");
			return map;
		}
	}

	@GetMapping("/customers")
	public Map<String, List<Customer>> read_all(Model model) {
		List<Customer> cust_list = customerDao.getCustomer();
		Map<String, List<Customer>> outter_map = new HashMap<>();
		outter_map.put("Data", cust_list);
		return outter_map;

	}

	@PutMapping("/customers")
	public Map<String, String> update(@RequestParam String email, @RequestParam String phone,
			@RequestParam String customer_address) {
		boolean updated = customerDao.updateCustomer(email, phone, customer_address);
		HashMap<String, String> map = new HashMap<>();
		if (updated) {
			map.put("Data", "Customer Updated Sucessfully.");
			return map;
		} else {
			map.put("Data", "Something went Wrong!!!");
			return map;
		}
	}

	@DeleteMapping("/customers")
	public Map<String, String> delete(@RequestParam String email, Model model) {
		System.out.println(email);
		boolean deleted = customerDao.deleteCustomer(email);
		HashMap<String, String> map = new HashMap<>();
		if (deleted) {
			map.put("Data", "Customer Deleted Sucessfully.");
			return map;
		} else {
			map.put("Data", "Something went Wrong!!!");
			return map;
		}
	}
}
