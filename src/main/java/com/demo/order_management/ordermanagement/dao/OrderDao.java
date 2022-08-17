package com.demo.order_management.ordermanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.order_management.ordermanagement.model.Order;

@Repository
public interface OrderDao extends JpaRepository<Order, Long>{
	
	public Order findByOrderId(Long id);
	

}
