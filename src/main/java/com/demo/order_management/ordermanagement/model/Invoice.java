package com.demo.order_management.ordermanagement.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "invoice")
public class Invoice {
	@Id
	@Column(name = "invoice_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long invoiceId;
	
	@Column(name = "invoice_date", nullable = false)
	//@DateTimeFormat(pattern = "dd/MM/yyyy")
	@GeneratedValue()
	private Date invoiceDate;
	
	@PrePersist
	private void onCreate() {
		invoiceDate = (Date) new java.util.Date(); 
	}
	
	@Column(name = "sub_total", nullable = false)
	private Double subTotal;
	
	@Column(name = "shipping_address", nullable = false)
	private String shippingAddress;
	
	@Column(name = "payment", nullable = false)
	private String payment;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;
	
	
	//@JoinColumn(name="invoice_id")
	@OneToMany(mappedBy = "invoice",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Order> order = new HashSet<>();
}
