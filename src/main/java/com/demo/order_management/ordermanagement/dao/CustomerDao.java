package com.demo.order_management.ordermanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.order_management.ordermanagement.model.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Long> {
	Customer findByCustomerId(Long id);
	Customer findByEmail(String email);
	boolean existsByEmail(String email);
	void deleteByEmail(String email);
	
}
