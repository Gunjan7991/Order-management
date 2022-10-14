package com.demo.order_management.ordermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.order_management.ordermanagement.model.Order;
import com.demo.order_management.ordermanagement.request.OrderRequest;
import com.demo.order_management.ordermanagement.response.BaseOrderResponse;
import com.demo.order_management.ordermanagement.service.OrderService;

@RestController
@RequestMapping(value = "/amazon/v1/api")
public class OrderController {

	@Autowired
	OrderService orderService;

	@PostMapping("/orders")
	public ResponseEntity<BaseOrderResponse> create(@RequestBody OrderRequest orderRequest) {
		BaseOrderResponse response = new BaseOrderResponse();
		response = orderService.create(orderRequest);
		if (response.getData() == null) {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("/orders/{id}")
	public ResponseEntity<BaseOrderResponse> read(@PathVariable("id") Long id) {
		BaseOrderResponse response = new BaseOrderResponse();
		response = orderService.getOrder(id);
		if (response.getData() == null) {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/orders")
	public ResponseEntity<List<Order>> read() {
		List<Order> response = orderService.get_all();
		if (response == null) {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping("/orders/{id}")
	public ResponseEntity<BaseOrderResponse> update(@PathVariable("id") Long id, @RequestBody OrderRequest orderRequest) {
		BaseOrderResponse response = new BaseOrderResponse();
		response = orderService.update(id, orderRequest);
		if (response.getData() == null) {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/orders/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		BaseOrderResponse response = new BaseOrderResponse();
		response = orderService.getOrder(id);
		if (response.getData() == null) {
			return new ResponseEntity<>("Unsucessfull Request!!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Deleted!!", HttpStatus.NO_CONTENT);
	}
	

}
