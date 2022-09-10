package com.demo.order_management.ordermanagement.Controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.order_management.ordermanagement.controller.ProductController;
import com.demo.order_management.ordermanagement.model.Product;
import com.demo.order_management.ordermanagement.request.ProductRequest;
import com.demo.order_management.ordermanagement.service.ProductService;

@RunWith(SpringRunner.class)
public class ProductControllerTest {
	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@InjectMocks
	ProductController productController;
	@Mock
	ProductService productService;

	private ProductRequest getProductRequest() {
		ProductRequest productRequest = new ProductRequest();
		productRequest.setProduct_name("Mac_Book pro");
		productRequest.setPrice(1799.99);
		productRequest.setQuantity(30);
		return productRequest;
	}

	private Product productResponse() {
		Product product = new Product();
		product.setProduct_name("Mac_Book pro");
		product.setProduct_price(1799.99);
		product.setProduct_quantity(30);
		product.setProductId(123L);
		return product;
	}

	private Product nullProductResponse() {
		Product product = new Product();
		return product;
	}

	@Test
	public void readTest() {
		Mockito.when(productService.find(Mockito.any())).thenReturn(productResponse());
		ResponseEntity<Product> response = productController.read(123L);
		assertNotNull(response.getBody().getProductId());
	}

	@Test
	public void readExceptionTest() {
		Mockito.when(productService.find(Mockito.any())).thenReturn(nullProductResponse());
		ResponseEntity<Product> response = productController.read(123L);
		assertNull(response.getBody().getProductId());
	}

	@Test
	public void readallTest() {
		List<Product> list = new ArrayList<>();
		list.add(productResponse());
		Mockito.when(productService.find_all()).thenReturn(list);
		ResponseEntity<List<Product>> response = productController.readall();
		assertNotNull(response);
	}

	@Test
	public void readallExceptionTest() {
		List<Product> list = new ArrayList<>();
		Mockito.when(productService.find_all()).thenReturn(list);
		ResponseEntity<List<Product>> response = productController.readall();
		assertNotNull(response);
	}

	@Test
	public void createTest() {
		Mockito.when(productService.create(Mockito.any())).thenReturn(productResponse());
		ResponseEntity<Product> response = productController.create(getProductRequest());
		assertNotNull(response.getBody().getProductId());
	}

	@Test
	public void createExceptionTest() {
		Mockito.when(productService.create(Mockito.any())).thenReturn(nullProductResponse());
		ResponseEntity<Product> response = productController.create(getProductRequest());
		assertNull(response.getBody().getProductId());
	}

	@Test
	public void updateTest() {
		Mockito.when(productService.update(Mockito.any(), Mockito.any())).thenReturn(productResponse());
		ResponseEntity<Product> response = productController.update(123L, getProductRequest());
		assertNotNull(response.getBody().getProductId());
	}

	@Test
	public void updateExceptionTest() {
		Mockito.when(productService.update(Mockito.any(), Mockito.any())).thenReturn(nullProductResponse());
		ResponseEntity<Product> response = productController.update(123L, getProductRequest());
		assertNull(response.getBody().getProductId());
	}

	@Test
	public void deleteTest() {
		Mockito.when(productService.delete(Mockito.any())).thenReturn(true);
		ResponseEntity<Boolean> response = productController.delete(123L);
		assertNotNull(response);
	}

	@Test
	public void deleteExceptionTest() {
		Mockito.when(productService.delete(Mockito.any())).thenReturn(false);
		ResponseEntity<Boolean> response = productController.delete(123L);
		assertNotNull(response);
	}
}
