package com.demo.order_management.ordermanagement.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.order_management.ordermanagement.dao.OrderDao;
import com.demo.order_management.ordermanagement.model.Order;
import com.demo.order_management.ordermanagement.request.OrderRequest;

@Service
public class OrderService {
	@Autowired
	OrderDao orderDao;

	public Order create(OrderRequest orderRequest) {
		Order order = new Order();
		try {
			order.setCustomer(orderRequest.getCustomer());
			order.setProduct(orderRequest.getProduct());
			order.setQuantity(orderRequest.getQuantity());
			order.setBilling_total(orderRequest.getBilling_total());
			order.setPayment(orderRequest.getPayment());
			order.setShipping_address(orderRequest.getShipping_address());
			order = orderDao.save(order);
			return order;
		} catch (Exception e) {
			// System.out.println(e);
		}
		return order;
	}

	public Order getOrder(Long id) {
		Order order = new Order();
		try {
			if (orderDao.existsById(id))
				order = orderDao.findByOrderId(id);
			return order;

		} catch (Exception e) {
			// System.out.println(e);
		}
		return order;
	}

	public List<Order> get_all() {
		List<Order> order = new LinkedList<Order>();
		try {
			order = orderDao.findAll();
			return order;

		} catch (Exception e) {
			// System.out.println(e);
		}

		return order;
	}
	
	public boolean delete(Long id, OrderRequest orderRequest) {
		try {
			if (orderDao.existsById(id)) {
				orderDao.deleteById(id);
				return true;
			}
			return false;

		} catch (Exception e) {
			// System.out.println(e);
		}
		return true;
	}
	
	
	public Order update(Long id, OrderRequest orderRequest) {
		Order order = new Order();
		try {
			if (orderDao.existsById(id))
				order = orderDao.findByOrderId(id);
			
			order.setCustomer(orderRequest.getCustomer());
			order.setProduct(orderRequest.getProduct());
			order.setQuantity(orderRequest.getQuantity());
			order.setBilling_total(orderRequest.getBilling_total());
			order.setPayment(orderRequest.getPayment());
			order.setShipping_address(orderRequest.getShipping_address());
			order = orderDao.save(order);
			return order;
		} catch (Exception e) {
			// System.out.println(e);
		}
		return order;
	}
	
	
	
	

}
