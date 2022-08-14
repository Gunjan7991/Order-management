package com.demo.order_management.ordermanagement.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.demo.order_management.ordermanagement.model.Customer;



@Repository
public class CustomerDao implements customer_template{
	@Autowired
	DataSource dataSource;
	
	
	@SuppressWarnings("deprecation")
	@Override
	public Customer getCustomer(String email) { // read
		String sql = "Select * from customer where email = ?";
		Object[] data = { email };
		Customer std = new Customer();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		try {
			std = jdbcTemplate.queryForObject(sql, data, new BeanPropertyRowMapper<>(Customer.class));
			return std;
		} catch (EmptyResultDataAccessException e) {
			//System.out.println("Exception: " + e);
			System.out.println("No such student found with email : " + email);
		}
		return std;
	}
	
	
	@Override
	public boolean addCustomer(Customer customer) { // create
		String sql = "Insert into customer(customer_name, email, customer_address, phone, billing_info) Values(?,?,?,?,?)";
		Object[] data = { customer.getCustomer_name(), customer.getEmail(), customer.getCustomer_address(), customer.getPhone(), customer.getBilling_info() };
		int row = 0;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		try {
		
			row = jdbcTemplate.update(sql, data);
			return row>0;
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			System.out.println("Number of row affected is: " + row);
		}
		return false;
	}

	@Override
	public List<Customer> getCustomer() { // read_all
		String sql = "Select * from customer";
		List<Customer> std = null;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		try {
			std = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Customer.class));
			return std;
		} catch (EmptyResultDataAccessException e) {
			//System.out.println("Exception: " + e);
			System.out.println("Database empty!!");
		} 
		return std;
	}

	@Override
	public boolean updateCustomer(String email, String phone, String customer_address) { // update
		String sql = "Update customer Set phone=?, customer_address=? where email = ?";
		Object[] data = { phone, customer_address, email };
		int row = 0;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		try {
			row = jdbcTemplate.update(sql, data);
			return row>0;
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			System.out.println("Number of row affected is: " + row);
		}
		return false;
	}

	@Override
	public boolean deleteCustomer(String email) { // delete
		String sql = "Delete from customer where email = ?";
		Object[] data = { email };
		int row = 0;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		try {
			row = jdbcTemplate.update(sql, data);
			return row>0;
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			System.out.println("Number of row affected is: " + row);
		}
		return false;
	}
	
	
	

}
