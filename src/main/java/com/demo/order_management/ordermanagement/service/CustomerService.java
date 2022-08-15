package com.demo.order_management.ordermanagement.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.order_management.ordermanagement.dao.CustomerDao;
import com.demo.order_management.ordermanagement.model.Customer;
import com.demo.order_management.ordermanagement.request.CustomerRequest;

@Service
public class CustomerService {

	@Autowired
	CustomerDao customerDao;

	public Customer getCustomer(Long id) {
		Customer customer = new Customer();
		try {
			customer = customerDao.findByCustomerId(id);
			return customer;
		} catch (Exception e) {
			// System.out.println("Exception: "+ e);
		}
		return customer;
	}

	public Customer getCustomerbyEmail(String email) {
		Customer customer = new Customer();
		try {
			customer = customerDao.findByEmail(email);
			return customer;
		} catch (Exception e) {
			// System.out.println("Exception: "+ e);
		}
		return customer;
	}

	public List<Customer> getCustomers() {
		List<Customer> customer = new LinkedList<Customer>();
		try {
			customer = customerDao.findAll();
			return customer;
		} catch (Exception e) {
			// System.out.println("Exception: "+ e);
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
		} catch (Exception e) {
			// System.out.println("Exception: "+ e);
		}
		return customer;
	}

	public boolean deleteCustomerbyID(Long id) {
		try {
			if (customerDao.existsById(id)) {
				customerDao.deleteById(id);
				return true;
			}
			return false;
		} catch (Exception e) {
			// System.out.println("Exception: "+ e);
		}
		return false;
	}

	public boolean deleteCustomerbyEmail(String email) {
		try {
			if (customerDao.existsByEmail(email)) {
				customerDao.deleteByEmail(email);
				;
				return true;
			}
			return false;
		} catch (Exception e) {
			// System.out.println("Exception: "+ e);
		}
		return false;
	}

	public Customer update(Long id, CustomerRequest customerRequest) {
		Customer customer = new Customer();
		try {
			if (customerDao.existsById(id)) {
				customer.setCustomerId(id);
				if (customerRequest.getEmail() != "" && customerRequest.getEmail() != null)
					customer.setEmail(customerRequest.getEmail());
				if (customerRequest.getBilling_info() != "" && customerRequest.getBilling_info() != null)
					customer.setBilling_info(customerRequest.getBilling_info());
				if (customerRequest.getCustomer_address() != "" && customerRequest.getCustomer_address() != null)
					customer.setCustomer_name(customerRequest.getCustomer_address());
				if (customerRequest.getPhone() != "" && customerRequest.getPhone() != null)
					customer.setPhone(customerRequest.getPhone());
				customer = customerDao.save(customer);
				return customer;
			}
		} catch (Exception e) {
			// System.out.println("Exception: "+ e);
		}
		return customer;
	}

	public Customer updatebyEmail(String email, CustomerRequest customerRequest) {
		Customer customer = new Customer();
		try {
			if (customerDao.existsByEmail(email)) {
				customer.setEmail(email);
				if (customerRequest.getBilling_info() != "" && customerRequest.getBilling_info() != null)
					customer.setBilling_info(customerRequest.getBilling_info());
				if (customerRequest.getCustomer_address() != "" && customerRequest.getCustomer_address() != null)
					customer.setCustomer_name(customerRequest.getCustomer_address());
				if (customerRequest.getPhone() != "" && customerRequest.getPhone() != null)
					customer.setPhone(customerRequest.getPhone());
				customer = customerDao.save(customer);
				return customer;
			}
		} catch (Exception e) {
			// System.out.println("Exception: "+ e);
		}
		return customer;
	}
}
