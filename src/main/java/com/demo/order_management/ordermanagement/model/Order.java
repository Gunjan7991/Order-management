package com.demo.order_management.ordermanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "bill_order")
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	
	@Id
	@Column(name = "order_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Product product;

	@Column(name = "quantity", nullable = false)
	private Integer quantity;

	@Column(name = "billing_total", nullable = false)
	private Double total;

	
}
