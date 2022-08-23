package com.demo.order_management.ordermanagement.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.demo.order_management.ordermanagement.dao.OrderDao;
import com.demo.order_management.ordermanagement.model.Order;
import com.demo.order_management.ordermanagement.request.OrderRequest;
import com.demo.order_management.ordermanagement.response.BaseOrderResponse;

@Service
public class OrderService {
	@Autowired
	OrderDao orderDao;

	public BaseOrderResponse create(OrderRequest orderRequest) {
		BaseOrderResponse response = new BaseOrderResponse();
		Order order = new Order();
		try {
			order.setProduct(orderRequest.getProduct());
			order.setQuantity(orderRequest.getQuantity());
			order.setTotal(orderRequest.getTotal());
			order = orderDao.save(order);
			response.setData(order);
			response.setMessage(" Order created sucessfully. ");
			return response;
		} catch (Exception e) {
			// System.out.println(e);
		}
		response.setData(null);
		response.setMessage(" Something went wrong!! ");
		return response;
	}

	public BaseOrderResponse getOrder(Long id) {
		BaseOrderResponse response = new BaseOrderResponse();
		Order order = new Order();
		try {
			if (orderDao.existsById(id)) {
				order = orderDao.findByOrderId(id);
				response.setData(order);
				response.setMessage(" Order fetched sucessfully. ");
			}
			
			return response;

		} catch (Exception e) {
			// System.out.println(e);
		}
		response.setData(null);
		response.setMessage(" Something went wrong!! ");
		return response;
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
	
	public List<Order> get_all_withPagination(Integer page_number, Integer page_size) {
		List<Order> order = new LinkedList<Order>();
		
		try {
			Page<Order> orderPageData  = orderDao.findAll(PageRequest.of(page_number, page_size, Sort.by("total").ascending()));
			for(Order ord: orderPageData) {
				order.add(ord);
			}
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
	
	
	public BaseOrderResponse update(Long id, OrderRequest orderRequest) {
		BaseOrderResponse response = new BaseOrderResponse();
		Order order = new Order();
		try {
			if (orderDao.existsById(id))
				order = orderDao.findByOrderId(id);
		
			order.setProduct(orderRequest.getProduct());
			order.setQuantity(orderRequest.getQuantity());
			order.setTotal(orderRequest.getTotal());
			order = orderDao.save(order);
			response.setData(order);
			response.setMessage(" Order updated sucessfully. ");
			return response;
		} catch (Exception e) {
			// System.out.println(e);
		}
		response.setData(null);
		response.setMessage(" Something went wrong!! ");
		return response;
	}
	
	
	
	

}
