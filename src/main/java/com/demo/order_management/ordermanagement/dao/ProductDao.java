package com.demo.order_management.ordermanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.order_management.ordermanagement.model.Product;

public interface ProductDao extends JpaRepository<Product, Long>{
	Product findByProductId(Long id);

}
