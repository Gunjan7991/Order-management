package com.demo.order_management.ordermanagement.controller;

import java.util.LinkedList;
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

import com.demo.order_management.ordermanagement.model.Product;
import com.demo.order_management.ordermanagement.request.ProductRequest;
import com.demo.order_management.ordermanagement.service.ProductService;

@RestController
@RequestMapping(value = "/amazon/v1/api")
public class ProductController {

	@Autowired
	ProductService productService;

	
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> read(@PathVariable("id") Long id) {
		Product product = productService.find(id);
		if (product.getProductId() == null) {
			return new ResponseEntity<>(product, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@GetMapping("/products")
	public ResponseEntity<List<Product>> readall() {
		List<Product> product = new LinkedList<Product>();
		product = productService.find_all();
		if (product.isEmpty()) {
			return new ResponseEntity<>(product, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@PostMapping("/products")
	public ResponseEntity<Product> create(@RequestBody ProductRequest productRequest) {
		Product product = productService.create(productRequest);
		if (product.getProductId() == null) {
			return new ResponseEntity<>(product, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(product, HttpStatus.CREATED);
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
		if (productService.delete(id)) {
			return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);

	}

	@PutMapping("/products/{id}")
	public ResponseEntity<Product> update(@PathVariable("id") Long id, @RequestBody ProductRequest productRequest) {
		Product product = new Product();
		product = productService.update(id, productRequest);
		if (product.getProductId() == null)
			return new ResponseEntity<>(product, HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(product, HttpStatus.ACCEPTED);
	}

}
