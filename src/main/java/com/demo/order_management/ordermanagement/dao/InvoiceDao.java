package com.demo.order_management.ordermanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.order_management.ordermanagement.model.Invoice;

public interface InvoiceDao extends JpaRepository<Invoice, Long>{
	public Invoice findByInvoiceId(Long id);
}
