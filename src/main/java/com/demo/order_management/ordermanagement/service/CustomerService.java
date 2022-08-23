package com.demo.order_management.ordermanagement.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.demo.order_management.ordermanagement.dao.CustomerDao;
import com.demo.order_management.ordermanagement.model.Customer;
import com.demo.order_management.ordermanagement.request.CustomerRequest;
import com.demo.order_management.ordermanagement.response.BaseCustomerResponse;
import com.demo.order_management.ordermanagement.response.CustomerResponse;

@Service
public class CustomerService {

	@Autowired
	CustomerDao customerDao;

	public BaseCustomerResponse getCustomer(Long id) { // also check how basecustomerresponse class as well as customerresponse  class was made.
		BaseCustomerResponse response = new BaseCustomerResponse();
		CustomerResponse custResponse = new CustomerResponse();
		Customer customer = new Customer();
		try {
			
			
			customer = customerDao.findByCustomerId(id);
			custResponse.setCustomerId(customer.getCustomerId());
			custResponse.setCustomerName(customer.getCustomerName());
			custResponse.setEmail(customer.getEmail());
			custResponse.setPassword(customer.getPassword());
			custResponse.setCustomerAddress(customer.getCustomerAddress());
			response.setData(custResponse);
			response.setMessage("Customer value was sucessfully retrived.");
			return response;
		} catch (Exception e) {
			// System.out.println("Exception: "+ e);
			response.setData(custResponse);
			response.setMessage("No value for ID: "+id+" was found!!");
			return response;
		}
		
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


	public List<Customer> getCustomerswithpagination(Integer page_number, Integer page_size) { // see the example of pagination here
		List<Customer> customer = new ArrayList<>();
		System.out.println(page_number+" "+ page_size);
		try {
			Page<Customer> customerPage = customerDao.findAll(PageRequest.of(page_number, page_size, Sort.by("customerName").ascending())); // make sure to have the name of your variable in standard java convention i.e(variableName)  and not like this i.e(varibale_name)
			//Page<Customer> customerPage = customerDao.findAll(PageRequest.of(page_number, page_size, Sort.b)))
			System.out.println(customerPage);
			for(Customer custom: customerPage) {
				customer.add(custom);
			}
			System.out.println(customer);
			return customer;
		} catch (Exception e) {
			System.out.println("Exception: "+ e);
		}
		return customer;
	}
	
	public Customer addCustomer(CustomerRequest customerRequest) {
		Customer customer = new Customer();
		try {
			customer.setCustomerName(customerRequest.getCustomerName());
			customer.setEmail(customerRequest.getEmail());
			customer.setPhone(customerRequest.getPhone());
			customer.setPassword(customerRequest.getPassword());
			customer.setBillingInfo(customerRequest.getBillingInfo());
			customer.setCustomerAddress(customerRequest.getCustomerAddress());
			customer = customerDao.save(customer);
			System.out.println(customer);
			return customer;

		} catch (Exception e) {
			 System.out.println("Exception: "+ e);
		}
		System.out.println(customer);
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
				customer = customerDao.findByCustomerId(id);
				if (customerRequest.getEmail() != "" && customerRequest.getEmail() != null)
					customer.setEmail(customerRequest.getEmail());
				if (customerRequest.getBillingInfo() != "" && customerRequest.getBillingInfo() != null)
					customer.setBillingInfo(customerRequest.getBillingInfo());
				if (customerRequest.getCustomerAddress() != "" && customerRequest.getCustomerAddress() != null)
					customer.setCustomerAddress(customerRequest.getCustomerAddress());
				if (customerRequest.getPhone() != "" && customerRequest.getPhone() != null)
					customer.setPhone(customerRequest.getPhone());
				if (customerRequest.getPassword() != "" && customerRequest.getPassword() != null)
					customer.setPassword(customerRequest.getPassword());
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
				customer = customerDao.findByEmail(email);
				if (customerRequest.getBillingInfo() != "" && customerRequest.getBillingInfo() != null)
					customer.setBillingInfo(customerRequest.getBillingInfo());
				if (customerRequest.getCustomerAddress() != "" && customerRequest.getCustomerAddress() != null)
					customer.setCustomerAddress(customerRequest.getCustomerAddress());
				if (customerRequest.getPhone() != "" && customerRequest.getPhone() != null)
					customer.setPhone(customerRequest.getPhone());
				if (customerRequest.getPassword() != "" && customerRequest.getPassword() != null)
					customer.setPassword(customerRequest.getPassword());
				customer = customerDao.save(customer);
				return customer;
			}
		} catch (Exception e) {
			// System.out.println("Exception: "+ e);
		}
		return customer;
	}
}
