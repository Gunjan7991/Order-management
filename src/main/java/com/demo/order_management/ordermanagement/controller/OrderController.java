package com.demo.order_management.ordermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.order_management.ordermanagement.model.Order;
import com.demo.order_management.ordermanagement.request.OrderRequest;
import com.demo.order_management.ordermanagement.response.BaseOrderResponse;
import com.demo.order_management.ordermanagement.service.OrderService;

@RestController
@RequestMapping(value="/amazon/v1/api")
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@PostMapping("/orders")
	public ResponseEntity<BaseOrderResponse> create(@RequestBody OrderRequest orderRequest){
		BaseOrderResponse response =  new BaseOrderResponse();
		response =  orderService.create(orderRequest);
		if(response.getData() == null) {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	
}
